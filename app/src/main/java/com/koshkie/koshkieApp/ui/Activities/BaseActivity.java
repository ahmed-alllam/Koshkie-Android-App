/*
 * Copyright (c) 2019. Code Written and tested by Ahmed Emad.
 */

package com.koshkie.koshkieApp.ui.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.koshkie.koshkieApp.PreferencesManager;

abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String language = PreferencesManager.getPreference(this, PreferencesManager.LANGUAGE_PREFERENCE, "");
        if (!"".equals(language)) {
            PreferencesManager.changeLocale(this, language);
        }
    }
}
