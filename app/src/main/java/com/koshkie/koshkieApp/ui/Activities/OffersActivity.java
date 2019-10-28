/*
 * Copyright (c) 2019. Code Written and tested by Ahmed Emad.
 */

package com.koshkie.koshkieApp.ui.Activities;

import android.os.Bundle;

import com.koshkie.koshkieApp.R;

public class OffersActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);
        setTitle(R.string.title_activity_offers);
    }
}
