package io.github.netgeek.asciiwarehouse.binding;

import android.databinding.BindingAdapter;
import android.util.TypedValue;
import android.widget.TextView;

public class CustomBindingAdapter {

    @BindingAdapter("android:textSize")
    public static void setTextSize(TextView textView, int textSize) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }
}