package com.example.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface FakeStoreApi {
    @GET("/products") // Replace with your API endpoint
    Call<List<ModelClass>> getProducts();
}
