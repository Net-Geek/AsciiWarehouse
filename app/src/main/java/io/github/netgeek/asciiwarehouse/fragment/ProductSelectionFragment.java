package io.github.netgeek.asciiwarehouse.fragment;

import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import io.github.netgeek.asciiwarehouse.R;
import io.github.netgeek.asciiwarehouse.adapter.ProductsRecyclerAdapter;
import io.github.netgeek.asciiwarehouse.api.ProductAPI;
import io.github.netgeek.asciiwarehouse.constant.Constants;
import io.github.netgeek.asciiwarehouse.converter.NDJsonConverterFactory;
import io.github.netgeek.asciiwarehouse.databinding.FragmentProductSelectionBinding;
import io.github.netgeek.asciiwarehouse.model.Product;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A placeholder fragment containing a simple view.
 */
public class ProductSelectionFragment extends Fragment {

    ProgressBar progressBar;
    RecyclerView productRecyclerView;
    FragmentProductSelectionBinding fragmentProductSelectionBinding;

    private ProductAPI productAPI;
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

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 1;
            }
        });

        productRecyclerView.setAdapter(productsRecyclerAdapter);
        productRecyclerView.setLayoutManager(gridLayoutManager);
        initProducts();

        return fragmentProductSelectionBinding.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void initProducts() {
        initProductAPI();

        Call<List<Product>> products = productAPI.products();

        products.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Response<List<Product>> response, Retrofit retrofit) {
                progressBar.setVisibility(View.GONE);
                productsRecyclerAdapter.setProducts(response.body());
                for (Product product : response.body()) {

                    Log.e("product:", product.getFace() + " " + product.getId());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("you", t.toString());
            }
        });


    }

    private void initProductAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.apiUrl)
                .addConverterFactory(NDJsonConverterFactory.create())
                .build();
        productAPI = retrofit.create(ProductAPI.class);
    }

    public void setSpanCount(int spanCount){
        if (gridLayoutManager != null){
            gridLayoutManager.setSpanCount(spanCount);
            productsRecyclerAdapter.notifyDataSetChanged();

        }
    }
}
