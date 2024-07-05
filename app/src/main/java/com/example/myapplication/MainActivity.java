package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity {

    Button Button1, Button2, Button3, Button4;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.activity_main));
        Button1 = findViewById(R.id.ButtonApplication1);
        Button2 = findViewById(R.id.ButtonApplication2);
        Button3 = findViewById(R.id.ButtonApplication3);
        Button4 = findViewById(R.id.ButtonApplication4);

        loadFragment(R.id.timerFragment, new DigitalClockFragment());


        addFragmentToClickListener(Button1, new TipCalculator());
        addFragmentToClickListener(Button2, new QuoteGenerator());
        addFragmentToClickListener(Button3, new Counter());
        addFragmentToClickListener(Button4, new UnitConverterFragment(Color.parseColor("#dddddd")));
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

//    public void successfulLogin() {
//        removeFragment(loginFragment);
//    }

    public void removeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragment).commit();
    }
}