package io.github.netgeek.discountasciiwarehouse.api;

import java.util.List;

import io.github.netgeek.discountasciiwarehouse.model.Product;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface ProductAPI {

    @GET("/api/search")
    Call<List<Product>> products();

    @GET("/api/search")
    Call<List<Product>> productsWithTag(@Query("q") String tag);
}
