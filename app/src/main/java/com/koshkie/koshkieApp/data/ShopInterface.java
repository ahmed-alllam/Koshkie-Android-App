/*
 * Copyright (c) 2020. Code Written and tested by Ahmed Emad.
 */

package com.koshkie.koshkieApp.data;

import com.koshkie.koshkieApp.models.ShopModel;
import com.koshkie.koshkieApp.models.ShopsResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ShopInterface {

    @GET("shops/{slug}")
    Call<ShopModel> retrieveShop(@Path("slug") String slug);

    @GET("shops/")
    Call<ShopsResponseModel> getShops(@Query("longitude") double longitude,
                                      @Query("latitude") double latitude,
                                      @Query("type") String type);

    @GET("shops")
    Call<ShopsResponseModel> searchShops(@Query("longitude") double longitude,
                                         @Query("latitude") double latitude,
                                         @Query("type") String type,
                                         @Query("search") String search);
}
