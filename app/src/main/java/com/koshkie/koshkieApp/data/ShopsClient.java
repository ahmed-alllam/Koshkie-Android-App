/*
 * Copyright (c) 2020. Code Written and tested by Ahmed Emad.
 */

package com.koshkie.koshkieApp.data;

import com.koshkie.koshkieApp.models.ShopModel;
import com.koshkie.koshkieApp.models.ShopsResponseModel;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class ShopsClient {
    private static final String BASE_URL = "http://10.0.2.2/";
    private ShopInterface shopInterface;

    ShopsClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        shopInterface = retrofit.create(ShopInterface.class);
    }

    Call<ShopModel> retrieveShop(String slug) {
        return shopInterface.retrieveShop(slug);
    }

    Call<ShopsResponseModel> getShops(double longitude, double latitude, String type) {
        return shopInterface.getShops(longitude, latitude, type);
    }

    Call<ShopsResponseModel> searchShops(double longitude, double latitude, String type,
                                         String search) {
        return shopInterface.searchShops(longitude, latitude, type, search);
    }
}
