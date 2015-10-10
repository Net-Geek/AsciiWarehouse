package io.github.netgeek.asciiwarehouse.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.github.netgeek.asciiwarehouse.BR;
import io.github.netgeek.asciiwarehouse.R;
import io.github.netgeek.asciiwarehouse.constant.Constants;
import io.github.netgeek.asciiwarehouse.model.Product;
import io.github.netgeek.asciiwarehouse.util.ProductColorUtil;

public class ProductsRecyclerAdapter extends RecyclerView.Adapter<ProductsRecyclerAdapter.BindingHolder> {
    private List<Product> mProducts;

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public BindingHolder(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }

    public ProductsRecyclerAdapter() {
        this.mProducts = new ArrayList<>();
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int type) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_product, parent, false);
        return new BindingHolder(v);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        Product product = mProducts.get(position);
        int colorIndex = ProductColorUtil.getProductColorIndex(position);

        product.setPrimaryBackgroundColor(Constants.PRIMARY_BACKGROUND_COLORS[colorIndex]);
        product.setSecondaryBackgroundColor(Constants.SECONDARY_BACKGROUND_COLORS[colorIndex]);
        product.setPrimaryTextColor(Constants.PRIMARY_TEXT_COLORS[colorIndex]);
        product.setSecondaryTextColor(Constants.SECONDARY_TEXT_COLORS[colorIndex]);

        holder.getBinding().setVariable(BR.product, product);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public List<Product> getProducts(){
        return mProducts;
    }

    public void setProducts(List<Product> products) {
        mProducts.clear();
        mProducts.addAll(products);
        notifyItemRangeInserted(0, mProducts.size() - 1);
    }
}
