/*
 * Copyright (c) 2020. Code Written and tested by Ahmed Emad.
 */

package com.koshkie.koshkieApp.ui.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.koshkie.koshkieApp.R;


public class MedicinesFragment extends ShopsBaseFragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_grocieries, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void getShops() {
        super.getShops("M");
    }
}
