package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity {

    Button Button1, Button2, Button3, Button4;
    LoginFragment loginFragment;

    SwitchCompat switcher;
    boolean nightMode = false;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.activity_main));
        Button1 = findViewById(R.id.ButtonApplication1);
        Button2 = findViewById(R.id.ButtonApplication2);
        Button3 = findViewById(R.id.ButtonApplication3);
        Button4 = findViewById(R.id.ButtonApplication4);

        loadFragment(R.id.timerFragment, new DigitalClockFragment());

        switcher = findViewById(R.id.switchBtn);

        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMode = sharedPreferences.getBoolean("night", false);

        if(nightMode){
            switcher.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        addFragmentToClickListener(Button1, new TipCalculator());
        addFragmentToClickListener(Button2, new QuoteGenerator());
        addFragmentToClickListener(Button3, new Counter());
        addFragmentToClickListener(Button4, new UnitConverterFragment(Color.parseColor("#dddddd")));

        switcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nightMode){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night", false);
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night", true);
                }
                editor.apply();
            }
        });
    }

    public void loadFragment(int layout, Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(layout, fragment).commit();
    }

    public void loadFragmentToBackStack(int layout, Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null).add(layout, fragment).commit();
    }

    public void addFragmentToClickListener(Button button, Fragment fragment) {
        button.setOnClickListener((view) -> loadFragmentToBackStack(R.id.frameLayout, fragment));
    }

    public void successfulLogin() {
        removeFragment(loginFragment);
    }

    public void removeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragment).commit();
    }
}