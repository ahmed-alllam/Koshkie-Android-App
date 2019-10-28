/*
 * Copyright (c) 2019. Code Written and tested by Ahmed Emad.
 */

package com.koshkie.koshkieApp.ui.Activities;

import android.os.Bundle;

import com.koshkie.koshkieApp.R;

public class ContactusActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);
        setTitle(R.string.title_activity_contact_us);
    }
}
