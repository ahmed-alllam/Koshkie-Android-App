package com.koshkie.koshkieApp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(Splash.this, Splash.this.getNextActivity());
                Splash.this.startActivity(mainIntent);
                Splash.this.finish();
            }
        }, 1500);
    }

    private Class getNextActivity() {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        String language = prefs.getString(langPref, "");
        if ("".equals(language)) {
            return ChooseLangActivity.class;
        } else {
            return MainActivity.class;
        }
    }
}
