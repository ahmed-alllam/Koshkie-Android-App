/*
 * Copyright (c) 2020. Code Written and tested by Ahmed Emad.
 */

package com.koshkie.koshkieApp.ui.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.koshkie.koshkieApp.PreferencesManager;
import com.koshkie.koshkieApp.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // keeps the splash activity running for 1500ms
        // then launch either main activity or tutorial activity
        // depending on the shared preferences stored

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this, SplashActivity.this.getNextActivity());
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, 1500);
    }

    private Class getNextActivity() {
        //checks if the user has seen the tutorial --
        // if yes launch main activity
        // if he hasn't seen it , launch the tutorial activity

        String isFirst = PreferencesManager.getPreference(this, PreferencesManager.FIRST_LAUNCH_PREFERENCE, "true");
        if ("true".equals(isFirst)) {
            return TutorialActivity.class;
        }
        return MainActivity.class;
    }
}
