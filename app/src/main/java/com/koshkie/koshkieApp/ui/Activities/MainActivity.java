/*
 * Copyright (c) 2020. Code Written and tested by Ahmed Emad.
 */

package com.koshkie.koshkieApp.ui.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.koshkie.koshkieApp.R;
import com.koshkie.koshkieApp.ui.Adapters.ShopsViewPagerAdapter;
import com.koshkie.koshkieApp.ui.Fragments.FoodFragment;
import com.koshkie.koshkieApp.ui.Fragments.GrocieriesFragment;
import com.koshkie.koshkieApp.ui.Fragments.MedicinesFragment;
import com.koshkie.koshkieApp.ui.Fragments.ShopsBaseFragment;

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
        viewPager.setOffscreenPageLimit(3);


        //adds the fragments to the view pager adapter
        ArrayList<Class> fragments = new ArrayList<>();
        fragments.add(FoodFragment.class);
        fragments.add(GrocieriesFragment.class);
        fragments.add(MedicinesFragment.class);

        ShopsViewPagerAdapter shopsViewPagerAdapter = new ShopsViewPagerAdapter(this, fragments);
        viewPager.setAdapter(shopsViewPagerAdapter);


        TabLayout tabLayout = findViewById(R.id.tabLayout);

        //sets the tab layout with the view pager
        //and gives the tabs titles
        new TabLayoutMediator(tabLayout, viewPager, true, (tab, position) -> {
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
        }).attach();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            //closes the navigation drawer on back button pressed
            //if it's open
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //gets called when any of the navigation drawer items get clicked
        switch (item.getItemId()) {
            case R.id.orders:
                //launches orders activity
                Intent orders_intent = new Intent(this, OrdersActivity.class);
                startActivity(orders_intent);
                break;
            case R.id.offers:
                //launches offers activity
                Intent offers_intent = new Intent(this, OffersActivity.class);
                startActivity(offers_intent);
                break;
            case R.id.settings:
                //launches settings activity
                Intent settings_intent = new Intent(this, SettingsActivity.class);
                startActivity(settings_intent);
                break;
            case R.id.contact_us:
                //launches contact us activity
                Intent contact_intent = new Intent(this, ContactusActivity.class);
                startActivity(contact_intent);
                break;
            case R.id.terms:
                //launches terms activity
                Intent terms_intent = new Intent(this, TermsActivity.class);
                startActivity(terms_intent);
        }
        //closes the navigation drawer
        //after an items has been taped
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onActionBarItemSelected(View view) {
        //gets called whenever ac action bar item gets clicked
        switch (view.getId()) {
            case R.id.cart_layout:
                //launches cart activity
                Intent cart_intent = new Intent(this, CartActivity.class);
                startActivity(cart_intent);
                break;
            case R.id.navButton:
                //opens the navigation drawer
                drawer.openDrawer(GravityCompat.START);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                for (Fragment f : getSupportFragmentManager().getFragments()) {
                    f.getView().findViewById(R.id.recycler_view).setVisibility(View.VISIBLE);
                    f.getView().findViewById(R.id.no_gps).setVisibility(View.GONE);
                    ((ShopsBaseFragment) f).getShops();
                }
            }
        }
    }
}
