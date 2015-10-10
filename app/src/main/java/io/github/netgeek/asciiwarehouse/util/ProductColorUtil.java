package io.github.netgeek.asciiwarehouse.util;

public class ProductColorUtil {

    public static int getProductColorIndex(int position){
        return (position + 5) % 5;
    }
}
