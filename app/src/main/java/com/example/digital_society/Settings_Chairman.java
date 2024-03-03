package com.example.digital_society;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;


public class Settings_Chairman extends Fragment {

    SwitchCompat darkmode;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_settings__chairman,container,false);

        darkmode = v.findViewById(R.id.darkmode);

        darkmode.setChecked(false);

        darkmode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (darkmode.isChecked()){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

        return v;
    }
}