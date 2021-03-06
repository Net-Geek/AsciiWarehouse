package io.github.netgeek.asciiwarehouse.fragment;

import android.databinding.DataBindingUtil;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import io.github.netgeek.asciiwarehouse.R;
import io.github.netgeek.asciiwarehouse.adapter.ProductsRecyclerAdapter;
import io.github.netgeek.asciiwarehouse.api.ProductAPI;
import io.github.netgeek.asciiwarehouse.constant.Constants;
import io.github.netgeek.asciiwarehouse.converter.NDJsonConverterFactory;
import io.github.netgeek.asciiwarehouse.databinding.FragmentProductSelectionBinding;
import io.github.netgeek.asciiwarehouse.listener.EndlessScrollListener;
import io.github.netgeek.asciiwarehouse.model.Product;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Product selection fragment that houses the product recycler view
 */
public class ProductSelectionFragment extends Fragment {

    ProgressBar progressBar;
    RecyclerView productRecyclerView;
    FragmentProductSelectionBinding fragmentProductSelectionBinding;

    private ProductAPI productAPI;
    private Call<List<Product>> initProducts;
    private Call<List<Product>> loadMoreProducts;
    private Callback<List<Product>> initProductsCallback;
    private Callback<List<Product>> loadMoreProductsCallback;
    private ProductsRecyclerAdapter productsRecyclerAdapter;
    private GridLayoutManager gridLayoutManager;

    public ProductSelectionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentProductSelectionBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_selection, container, false);
        progressBar = fragmentProductSelectionBinding.progressbar;
        productRecyclerView = fragmentProductSelectionBinding.productRecyclerview;

        productsRecyclerAdapter = new ProductsRecyclerAdapter();
        gridLayoutManager = new GridLayoutManager(getContext(), 2);

        productRecyclerView.setAdapter(productsRecyclerAdapter);
        productRecyclerView.setLayoutManager(gridLayoutManager);
        productRecyclerView.addOnScrollListener(new EndlessScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore() {
                loadMoreProducts(productsRecyclerAdapter.getItemCount());
            }
        });

        if(savedInstanceState == null){
            initProducts();
        } else {
            initProductAPI();
            progressBar.setVisibility(View.GONE);

            List<Product> productList = savedInstanceState.getParcelableArrayList(Constants.productArrayKey);
            productsRecyclerAdapter.setProducts(productList);
        }

        return fragmentProductSelectionBinding.getRoot();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(Constants.productArrayKey, (ArrayList<? extends Parcelable>) productsRecyclerAdapter.getProducts());
    }

    private void initProducts() {
        initProductAPI();

        initProducts = productAPI.initProducts();

        initProductsCallback = new Callback<List<Product>>() {
            @Override
            public void onResponse(Response<List<Product>> response, Retrofit retrofit) {
                progressBar.setVisibility(View.GONE);
                productsRecyclerAdapter.setProducts(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                progressBar.setVisibility(View.GONE);
                final Snackbar snackbar = Snackbar.make(productRecyclerView, R.string.network_error, Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction("retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                        progressBar.setVisibility(View.VISIBLE);
                        initProducts.clone().enqueue(initProductsCallback);
                    }
                }).show();
            }
        };

        initProducts.enqueue(initProductsCallback);

    }

    private void loadMoreProducts(int productsToSkip) {
        loadMoreProducts = productAPI.productsWithSkip(productsToSkip);

        loadMoreProductsCallback = new Callback<List<Product>>() {
            @Override
            public void onResponse(Response<List<Product>> response, Retrofit retrofit) {
                productsRecyclerAdapter.addProducts(response.body());
                Log.e("products added", "total products: " + productsRecyclerAdapter.getItemCount());
                if (gridLayoutManager.findLastCompletelyVisibleItemPosition() == productsRecyclerAdapter.getItemCount() - 11) {
                    productRecyclerView.smoothScrollBy(0, 150);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                if (t instanceof ConnectException){
                    final Snackbar snackbar = Snackbar.make(productRecyclerView, R.string.network_error, Snackbar.LENGTH_INDEFINITE);
                    snackbar.setAction("retry", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            snackbar.dismiss();
                            loadMoreProducts.clone().enqueue(loadMoreProductsCallback);
                        }
                    }).show();
                }
            }
        };

        loadMoreProducts.enqueue(loadMoreProductsCallback);

    }

    private void initProductAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.apiUrl)
                .addConverterFactory(NDJsonConverterFactory.create())
                .build();
        productAPI = retrofit.create(ProductAPI.class);
    }

    public void setSpanCount(int spanCount) {
        if (gridLayoutManager != null) {
            gridLayoutManager.setSpanCount(spanCount);
            productsRecyclerAdapter.notifyDataSetChanged();
        }
    }
}
