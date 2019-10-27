/*
 * Copyright (c) 2019. Code Written and tested by Ahmed Emad.
 */

package com.koshkie.koshkieApp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;


class PagerAdapter extends FragmentStateAdapter {

    private Context mContext;

    PagerAdapter(FragmentManager fm, Lifecycle lifecycle, Context context) {
        super(fm, lifecycle);
        mContext = context;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new FoodFragment();
            case 1:
                return new GrocieriesFragment();
            case 2:
                return new MedicinesFragment();
            default:
                return new Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
//
//    @Override
//    public CharSequence getPageTitle(int position) {
//        switch (position) {
//            case 0:
//                return mContext.getString(R.string.food);
//            case 1:
//                return mContext.getString(R.string.grocieries);
//            case 2:
//                return mContext.getString(R.string.medicines);
//            default:
//                return null;
//        }
//    }

}
