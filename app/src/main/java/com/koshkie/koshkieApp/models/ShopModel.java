/*
 * Copyright (c) 2020. Code Written and tested by Ahmed Emad.
 */

package com.koshkie.koshkieApp.models;

import java.util.ArrayList;

public class ShopModel {
    private String profile_photo;
    private long phone_number;
    private String description;
    private String shop_type;
    private String name;
    private String slug;
    private double rating;
    private int reviews_count;
    private boolean is_active;
    private boolean is_open;
    private String currency;
    private float minimum_charge;
    private float delivery_fee;
    private float vat;
    private String opens_at;
    private String closes_at;
    private boolean has_offers;
    private ArrayList<String> tags;

    public ArrayList<String> getTags() {
        return tags;
    }

    public String getProfile_photo() {
        return profile_photo;
    }

    public long getPhone_number() {
        return phone_number;
    }

    public int getReviews_count() {
        return reviews_count;
    }

    public boolean isHas_offers() {
        return has_offers;
    }

    public String getDescription() {
        return description;
    }

    public String getShop_type() {
        return shop_type;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public double getRating() {
        return rating;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public boolean isIs_open() {
        return is_open;
    }

    public String getCurrency() {
        return currency;
    }

    public float getMinimum_charge() {
        return minimum_charge;
    }

    public float getDelivery_fee() {
        return delivery_fee;
    }

    public float getVat() {
        return vat;
    }

    public String getOpens_at() {
        return opens_at;
    }

    public String getCloses_at() {
        return closes_at;
    }
}
