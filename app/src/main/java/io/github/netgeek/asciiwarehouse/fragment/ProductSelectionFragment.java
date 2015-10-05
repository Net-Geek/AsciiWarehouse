package io.github.netgeek.asciiwarehouse.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.netgeek.asciiwarehouse.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class ProductSelectionFragment extends Fragment {

    public ProductSelectionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_selection, container, false);
    }
}
