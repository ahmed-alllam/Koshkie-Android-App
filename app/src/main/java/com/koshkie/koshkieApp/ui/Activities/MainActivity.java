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


        //adds the fragments to the view pager adapter
        ArrayList<Class> fragments = new ArrayList<>();
        fragments.add(FoodFragment.class);
        fragments.add(GrocieriesFragment.class);
        fragments.add(MedicinesFragment.class);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, fragments);
        viewPager.setAdapter(viewPagerAdapter);


        TabLayout tabLayout = findViewById(R.id.tabLayout);

        //sets the tab layout with the view pager
        //and gives the tabs titles
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
            case R.id.popup:
                //shows a popup used to choose
                // between sorting by items or by shops
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
