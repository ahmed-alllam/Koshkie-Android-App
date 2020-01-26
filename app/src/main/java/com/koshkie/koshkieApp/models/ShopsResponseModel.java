/*
 * Copyright (c) 2020. Code Written and tested by Ahmed Emad.
 */

package com.koshkie.koshkieApp.models;

import java.util.ArrayList;

public class ShopsResponseModel {
    private int limit;
    private int offset;
    private int count;
    private ArrayList<ShopModel> shops;

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }

    public int getCount() {
        return count;
    }

    public ArrayList<ShopModel> getShops() {
        return shops;
    }
}
