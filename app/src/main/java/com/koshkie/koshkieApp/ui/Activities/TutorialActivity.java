/*
 * Copyright (c) 2020. Code Written and tested by Ahmed Emad.
 */

package com.koshkie.koshkieApp.ui.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.koshkie.koshkieApp.PreferencesManager;
import com.koshkie.koshkieApp.R;
import com.koshkie.koshkieApp.ViewPagerAdapter;
import com.koshkie.koshkieApp.ui.Fragments.LanguageDialog;
import com.koshkie.koshkieApp.ui.Fragments.TutorialFragment1;
import com.koshkie.koshkieApp.ui.Fragments.TutorialFragment2;
import com.koshkie.koshkieApp.ui.Fragments.TutorialFragment3;

import java.util.ArrayList;

public class TutorialActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        //changes the shared preference is first launch to false in order to not launch this activity again
        PreferencesManager.setPreference(this, PreferencesManager.FIRST_LAUNCH_PREFERENCE, "false");

        ViewPager2 viewPager = findViewById(R.id.tutorials);

        //adds tutorial fragments to the view pager adapter
        ArrayList<Class> fragments = new ArrayList<>();
        fragments.add(TutorialFragment1.class);
        fragments.add(TutorialFragment2.class);
        fragments.add(TutorialFragment3.class);


        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, fragments);
        viewPager.setAdapter(viewPagerAdapter);


        //sets the three points tab layout to the view pager
        TabLayout tabLayout = findViewById(R.id.tabs);

        new TabLayoutMediator(tabLayout, viewPager, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                //tabs won't have names or any styling property, so this func is empty
            }
        }).attach();

        //creates margins between tab in the tab layout
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            View tab = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i);
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
            p.setMargins(0, 0, 30, 0);
            tab.requestLayout();
        }
    }

    public void launch_main(View view) {
        //tutorial ended , launch main activity
        view.setClickable(false);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void show_popup(View view) {
        //show alert dialog for choosing a language
        DialogFragment dialog = new LanguageDialog();
        dialog.show(getSupportFragmentManager(), "ChooseLanguageDialogFragment");
    }

    public void onLanguageSelected(String lang) {
        //if user chooses a different language then recreate the activity
        if (!PreferencesManager.getDefaultLocale(this).equals(lang)) {
            PreferencesManager.changeLocale(this, lang);
            startActivity(new Intent(this, TutorialActivity.class));
            finish();
        }
    }
}
