package io.github.netgeek.asciiwarehouse.fragment;

import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import io.github.netgeek.asciiwarehouse.R;
import io.github.netgeek.asciiwarehouse.adapter.ProductsRecyclerAdapter;
import io.github.netgeek.asciiwarehouse.databinding.FragmentProductSearchBinding;

/**
 * Product search fragment that houses the product recycler view
 */
public class ProductSearchFragment extends Fragment {

    ProgressBar progressBar;
    RecyclerView productRecyclerView;
    FragmentProductSearchBinding fragmentProductSearchBinding;

    private ProductsRecyclerAdapter productsRecyclerAdapter;
    private GridLayoutManager gridLayoutManager;


    public ProductSearchFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentProductSearchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_search, container, false);
        progressBar = fragmentProductSearchBinding.progressbar;
        productRecyclerView = fragmentProductSearchBinding.productRecyclerview;

        productsRecyclerAdapter = new ProductsRecyclerAdapter();
        gridLayoutManager = new GridLayoutManager(getContext(), 2);

        productRecyclerView.setAdapter(productsRecyclerAdapter);
        productRecyclerView.setLayoutManager(gridLayoutManager);

        progressBar.setVisibility(View.GONE);

        return fragmentProductSearchBinding.getRoot();
    }

}
