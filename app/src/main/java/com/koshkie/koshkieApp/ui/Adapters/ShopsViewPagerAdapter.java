/*
 * Copyright (c) 2020. Code Written and tested by Ahmed Emad.
 */

package com.koshkie.koshkieApp.ui.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;


public class ShopsViewPagerAdapter extends FragmentStateAdapter {

    private List<Class> fragments;


    public ShopsViewPagerAdapter(FragmentActivity activity, List<Class> fragments) {
        super(activity);
        this.fragments = fragments;

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        try {
            return (Fragment) fragments.get(position).newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return new Fragment();
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
