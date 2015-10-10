package io.github.netgeek.asciiwarehouse.binding;

import android.databinding.BindingAdapter;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

public class CustomBindingAdapter {

    @BindingAdapter("android:textSize")
    public static void setTextSize(TextView textView, int textSize) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }

    @BindingAdapter("android:textColor")
    public static void setTextColor(TextView textView, int resId) {
        textView.setTextColor(textView.getResources().getColor(resId));
    }

    @BindingAdapter("android:background")
    public static void setBackgroundColor(View view, int resId) {
        view.setBackgroundResource(resId);
    }
}