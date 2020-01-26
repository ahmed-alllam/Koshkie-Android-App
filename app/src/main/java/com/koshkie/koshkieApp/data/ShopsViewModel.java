/*
 * Copyright (c) 2020. Code Written and tested by Ahmed Emad.
 */

package com.koshkie.koshkieApp.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.koshkie.koshkieApp.models.ShopModel;
import com.koshkie.koshkieApp.models.ShopsResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopsViewModel extends ViewModel {
    public MutableLiveData<List<ShopModel>> shops = new MutableLiveData<>();
    public MutableLiveData<ShopModel> shop = new MutableLiveData<>();

    public void retrieveShop(String slug) {
        new ShopsClient().retrieveShop(slug).enqueue(new Callback<ShopModel>() {
            @Override
            public void onResponse(@NonNull Call<ShopModel> call, @NonNull Response<ShopModel> response) {
                shop.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<ShopModel> call, @NonNull Throwable t) {
                System.out.println("error");
                // not complete
            }
        });
    }

    public void getShops(double longitude, double latitude, String type) {
        new ShopsClient().getShops(longitude, latitude, type).enqueue(new Callback<ShopsResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ShopsResponseModel> call, @NonNull Response<ShopsResponseModel> response) {
                shops.setValue(response.body().getShops());
                System.out.println(response.body());
                // check if shops are empty
            }

            @Override
            public void onFailure(@NonNull Call<ShopsResponseModel> call, Throwable t) {
                System.out.println("error" + t.getMessage());
            }
        });
    }

    public void searchShops(double longitude, double latitude, String type, String search) {
        new ShopsClient().searchShops(longitude, latitude, type, search).enqueue(new Callback<ShopsResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ShopsResponseModel> call, @NonNull Response<ShopsResponseModel> response) {
                shops.setValue(response.body().getShops());
            }

            @Override
            public void onFailure(@NonNull Call<ShopsResponseModel> call, @NonNull Throwable t) {
                System.out.println("error");
                // not complete
            }
        });
    }
}
