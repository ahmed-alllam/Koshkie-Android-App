/*
 * Copyright (c) 2019. Code Written and tested by Ahmed Emad.
 */

package com.koshkie.koshkieApp.ui.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.viewpager2.widget.ViewPager2;

import com.koshkie.koshkieApp.R;
import com.koshkie.koshkieApp.ViewPagerAdapter;
import com.koshkie.koshkieApp.ui.Fragments.TutorialFragment1;
import com.koshkie.koshkieApp.ui.Fragments.TutorialFragment2;
import com.koshkie.koshkieApp.ui.Fragments.TutorialFragment3;

import java.util.ArrayList;


public class TutorialActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        ViewPager2 viewPager = findViewById(R.id.tutorials);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        ArrayList<Class> fragment = new ArrayList<>();
        fragment.add(TutorialFragment1.class);
        fragment.add(TutorialFragment2.class);
        fragment.add(TutorialFragment3.class);
        viewPagerAdapter.setFragmentsList(fragment);

    }

    public void next(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
