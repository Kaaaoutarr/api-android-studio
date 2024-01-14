// MainActivity.java
package com.example.api;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://fakestoreapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of FakeStoreApi
        FakeStoreApi fakeStoreApi = retrofit.create(FakeStoreApi.class);

        // Make the API call
        Call<List<ModelClass>> call = fakeStoreApi.getProducts();

        // Enqueue the call
        call.enqueue(new Callback<List<ModelClass>>() {
            @Override
            public void onResponse(@NonNull Call<List<ModelClass>> call, @NonNull Response<List<ModelClass>> response) {
                if (response.isSuccessful()) {
                    List<ModelClass> modelList = response.body();
                    if (modelList != null && !modelList.isEmpty()) {
                        // Update the ListView with the list of products
                        ProductAdapter productAdapter = new ProductAdapter(MainActivity.this, modelList);
                        ListView listView = findViewById(R.id.listView);
                        listView.setAdapter(productAdapter);
                    } else {
                        Log.e(TAG, "Response body is null or empty");
                    }
                } else {
                    // Handle unsuccessful response
                    Log.e(TAG, "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<ModelClass>> call, @NonNull Throwable t) {
                // Handle network errors
                Log.e(TAG, "Network error: " + t.getMessage());
            }
        });
    }
}
