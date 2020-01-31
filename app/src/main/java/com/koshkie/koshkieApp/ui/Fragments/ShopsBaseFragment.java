/*
 * Copyright (c) 2020. Code Written and tested by Ahmed Emad.
 */

package com.koshkie.koshkieApp.ui.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.koshkie.koshkieApp.R;
import com.koshkie.koshkieApp.data.ShopsViewModel;
import com.koshkie.koshkieApp.models.ShopModel;
import com.koshkie.koshkieApp.ui.Adapters.ShopsRecyclerViewAdapter;

import java.util.List;

public abstract class ShopsBaseFragment extends Fragment {
    View view;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                getShops();
            } else {
                view.findViewById(R.id.recycler_view).setVisibility(View.GONE);
                view.findViewById(R.id.no_gps).setVisibility(View.VISIBLE);
            }
            return view;
        } else {
            // todo :restore state
            // todo : add swipe refresh and pagignation
            return view;
        }
    }

    public abstract void getShops();

    @SuppressLint("MissingPermission")
    void getShops(String type) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        final ShopsRecyclerViewAdapter adapter = new ShopsRecyclerViewAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(adapter);

        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        double longitude = 0.0;
        double latitude = 0.0;
        if (location != null) {
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }

        ShopsViewModel shopsViewModel = new ViewModelProvider(this).get(ShopsViewModel.class);
        shopsViewModel.getShops(longitude, latitude, type);

        shopsViewModel.shops.observe(getViewLifecycleOwner(), new Observer<List<ShopModel>>() {
            @Override
            public void onChanged(List<ShopModel> postModels) {
                adapter.setList(postModels);
            }
        });

        shopsViewModel.error.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s.equals("No Shops")) {
                    System.out.println("no shops");
                    view.findViewById(R.id.recycler_view).setVisibility(View.GONE);
                    view.findViewById(R.id.no_shops).setVisibility(View.VISIBLE);
                } else {
                    System.out.println("no internet");
                    view.findViewById(R.id.recycler_view).setVisibility(View.GONE);
                    view.findViewById(R.id.no_internet).setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
