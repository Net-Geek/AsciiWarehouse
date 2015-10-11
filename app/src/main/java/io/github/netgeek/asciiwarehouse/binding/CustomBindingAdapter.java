package io.github.netgeek.asciiwarehouse.binding;

import android.databinding.BindingAdapter;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import io.github.netgeek.asciiwarehouse.util.FontUtil;

public class CustomBindingAdapter {

    @BindingAdapter("app:face")
    public static void setFace(TextView textView, String face) {
        if (android.os.Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            textView.setTypeface(FontUtil.get(textView.getContext(), "fonts/CODE2000.TTF"));
        }
        textView.setText(face);
    }

    @BindingAdapter("android:textSize")
    public static void setTextSize(TextView textView, int textSize) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }

    @BindingAdapter("android:textColor")
    public static void setTextColor(TextView textView, int color) {
        textView.setTextColor(color);
    }

    @BindingAdapter("android:background")
    public static void setBackgroundColor(View view, int resId) {
        view.setBackgroundResource(resId);
    }
}