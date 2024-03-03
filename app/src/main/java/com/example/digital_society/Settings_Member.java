package com.example.digital_society;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Settings_Member extends Fragment {

    SwitchCompat switchdarkmode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_settings__member,container,false);

        switchdarkmode = v.findViewById(R.id.switchdarkmode);

        switchdarkmode.setChecked(false);

        switchdarkmode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switchdarkmode.isChecked()){
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