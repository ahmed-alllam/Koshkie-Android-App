/*
 * Copyright (c) 2020. Code Written and tested by Ahmed Emad.
 */

package com.koshkie.koshkieApp.ui.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.koshkie.koshkieApp.PreferencesManager;
import com.koshkie.koshkieApp.R;
import com.koshkie.koshkieApp.ui.Activities.TutorialActivity;

public class LanguageDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        TextView myMsg = new TextView(getContext());
        myMsg.setText(R.string.choose_language);
        myMsg.setGravity(Gravity.CENTER_VERTICAL);
        myMsg.setTextSize(23);
        myMsg.setTextColor(Color.BLACK);
        myMsg.setPadding(55, 55, 55, 55);

        builder.setCustomTitle(myMsg)
                .setItems(R.array.languages, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String language = "";
                        switch (which) {
                            case 0:
                                language = PreferencesManager.ENGLISH_LANGUAGE;
                                break;
                            case 1:
                                language = PreferencesManager.ARABIC_LANGUAGE;
                        }
                        ((TutorialActivity) getActivity()).onLanguageSelected(language);
                    }
                });
        return builder.create();
    }
}
