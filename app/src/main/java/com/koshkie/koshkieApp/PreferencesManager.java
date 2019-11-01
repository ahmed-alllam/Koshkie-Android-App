/*
 * Copyright (c) 2019. Code Written and tested by Ahmed Emad.
 */

package com.koshkie.koshkieApp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import java.util.Locale;

public class PreferencesManager {

    //a helper class used to make some changes or get some data from the shared preferences


    //some useful constants
    public static final String ARABIC_LANGUAGE = "ar";
    public static final String ENGLISH_LANGUAGE = "en";
    public static final String LANGUAGE_PREFERENCE = "Language";
    public static final String FIRST_LAUNCH_PREFERENCE = "is first launch";


    public static String getPreference(Context context, String preference, String default_value) {
        //returns the value of a shared preference given the key
        SharedPreferences prefs = context.getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        return prefs.getString(preference, default_value);
    }

    public static void changeLocale(Activity context, String lang) {
        //changes the locale of the given activity
        //to the given language
        //then stores the change in the shared preferences
        Locale myLocale = new Locale(lang);
        Locale.setDefault(myLocale);
        Configuration config = new Configuration();
        config.setLocale(myLocale);
        context.getBaseContext().getResources().updateConfiguration(config, context.getBaseContext().getResources().getDisplayMetrics());
        setPreference(context, LANGUAGE_PREFERENCE, lang);
    }

    public static void setPreference(Context context, String preference, String value) {
        //makes some changes to the shared preferences
        //or adds new preference
        SharedPreferences prefs = context.getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(preference, value);
        editor.apply();
    }

    public static String getDefaultLocale(Activity context) {
        //returns the locale of a given activity
        return context.getBaseContext().getResources().getConfiguration().locale.getLanguage();
    }
}
