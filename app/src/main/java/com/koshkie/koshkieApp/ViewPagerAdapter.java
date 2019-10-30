/*
 * Copyright (c) 2019. Code Written and tested by Ahmed Emad.
 */

package com.koshkie.koshkieApp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;


public class ViewPagerAdapter extends FragmentStateAdapter {

    private List<Class> fragments;


    public ViewPagerAdapter(FragmentActivity activity, List<Class> fragments) {
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
