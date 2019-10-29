/*
 * Copyright (c) 2019. Code Written and tested by Ahmed Emad.
 */

package com.koshkie.koshkieApp.ui.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.koshkie.koshkieApp.R;
import com.koshkie.koshkieApp.ViewPagerAdapter;
import com.koshkie.koshkieApp.ui.Fragments.FoodFragment;
import com.koshkie.koshkieApp.ui.Fragments.GrocieriesFragment;
import com.koshkie.koshkieApp.ui.Fragments.MedicinesFragment;

import java.util.ArrayList;


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        NavigationView navigationView = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer_layout);
        navigationView.setNavigationItemSelectedListener(this);

        ViewPager2 viewPager = findViewById(R.id.viewpager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        ArrayList<Class> fragments = new ArrayList<>();
        fragments.add(FoodFragment.class);
        fragments.add(GrocieriesFragment.class);
        fragments.add(MedicinesFragment.class);
        viewPagerAdapter.setFragmentsList(fragments);


        TabLayout tabLayout = findViewById(R.id.tabLayout);

        new TabLayoutMediator(tabLayout, viewPager, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setIcon(R.drawable.food);
                        tab.setText(R.string.food);
                        break;
                    case 1:
                        tab.setIcon(R.drawable.grocieries);
                        tab.setText(R.string.grocieries);
                        break;
                    case 2:
                        tab.setIcon(R.drawable.medicines);
                        tab.setText(R.string.medicines);
                }
            }
        }).attach();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.orders:
                Intent orders_intent = new Intent(this, OrdersActivity.class);
                startActivity(orders_intent);
                break;
            case R.id.offers:
                Intent offers_intent = new Intent(this, OffersActivity.class);
                startActivity(offers_intent);
                break;
            case R.id.settings:
                Intent settings_intent = new Intent(this, SettingsActivity.class);
                startActivity(settings_intent);
                break;
            case R.id.contact_us:
                Intent contact_intent = new Intent(this, ContactusActivity.class);
                startActivity(contact_intent);
                break;
            case R.id.terms:
                Intent terms_intent = new Intent(this, TermsActivity.class);
                startActivity(terms_intent);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onActionBarItemSelected(View view) {
        switch (view.getId()) {
            case R.id.cart_layout:
                Intent cart_intent = new Intent(this, CartActivity.class);
                startActivity(cart_intent);
                break;
            case R.id.navButton:
                drawer.openDrawer(GravityCompat.START);
                break;
            case R.id.popup:
                PopupMenu popup = new PopupMenu(MainActivity.this, view);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        return true;
                    }
                });
                popup.show();
        }
    }
}
