/*
 * Copyright (c) 2019. Code Written and tested by Ahmed Emad.
 */

package com.koshkie.koshkieApp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.koshkie.koshkieApp.ui.Fragments.FoodFragment;
import com.koshkie.koshkieApp.ui.Fragments.GrocieriesFragment;
import com.koshkie.koshkieApp.ui.Fragments.MedicinesFragment;


public class PagerAdapter extends FragmentStateAdapter {


    public PagerAdapter(FragmentActivity activity) {
        super(activity);
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

}
