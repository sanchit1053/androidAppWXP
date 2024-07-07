package com.example.myapplication;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;

public class QuoteGenerator extends Fragment {

    public int index;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String[] quotes = {
                getString(R.string.story1),
                getString(R.string.story2),
                getString(R.string.story3),
                getString(R.string.story4),
                getString(R.string.story5),
        };

        View v = inflater.inflate(R.layout.quote_generator, container, false);

        Button button = v.findViewById(R.id.myButton);
        TextView quote = v.findViewById(R.id.myquote);
        TextView sampleText = v.findViewById(R.id.sampleText);

        // Set content descriptions for accessibility
        button.setContentDescription(getString(R.string.button_generate_quote_cd));
        sampleText.setContentDescription(getString(R.string.sample_text_cd));

        // Provide initial announcement for accessibility
        sampleText.announceForAccessibility(getString(R.string.sample_text_announcement));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                index = 0;
                do {
                    index = random.nextInt(quotes.length);
                } while (quotes[index] == null || quotes[index].isEmpty());

                quote.setText(quotes[index]);
            }
        });
        quote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fr = getActivity().getSupportFragmentManager();
                FragmentTransaction fmt = fr.beginTransaction();
                fmt.setCustomAnimations(R.anim.slide_up, 0, 0,R.anim.slide_up); // slide_down can be a reverse animation
                fmt.add(R.id.story,new Story_popUp(index));
                fmt.addToBackStack(null);
                fmt.commit();
            }
        });




        return v;
    }
}