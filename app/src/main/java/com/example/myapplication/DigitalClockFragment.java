package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class DigitalClockFragment extends Fragment {

    private TextView textClock;
    private Handler handler;
    private Runnable runnable;


    public DigitalClockFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_digital_cloc, container, false);
        textClock = view.findViewById(R.id.text_clock);
        return view;
    }

    @Override
    public void onStart(){
        super.onStart();



        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {
                updateTime();
                handler.postDelayed(this, 1000);
                Log.i("Time", "run()");
            }
        };
        Log.i("DG", "onCreate()");
    }

    @Override
    public void onResume(){
        super.onResume();
        handler.post(runnable);
    }

    @Override
    public void onStop(){
        super.onStop();
        handler.removeCallbacks(runnable);
        Log.i("DG", "onStop()");
    }


    private void updateTime(){
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
        String timeString = sdf.format(new Date(currentTime));

        textClock.setText(timeString);
    }
}