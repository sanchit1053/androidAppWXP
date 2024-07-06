package com.example.myapplication;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;


public class Story_popUp extends Fragment {
    private TextView story,name;
    private ImageButton mic,closeButton;
    public int i;
    private TextToSpeech sp;
    boolean isSpeaking = false; // Flag to track speech state

    public Story_popUp(int index) {
        i = index;
    }

    @SuppressLint("MissingInflatedId")
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
        String[] title = {
                getString(R.string.title1),
                getString(R.string.title2),
                getString(R.string.title3),
                getString(R.string.title4),
                getString(R.string.title5),
        };
        View v = inflater.inflate(R.layout.fragment_story_pop_up, container, false);
        story = v.findViewById(R.id.allstory);  // Initialize TextView
        mic = v.findViewById(R.id.mic);
        closeButton = v.findViewById(R.id.closeButton);
        name= v.findViewById(R.id.title);
        story.setText(quotes[i]);
        name.setText(title[i]);

        sp = new TextToSpeech(getActivity().getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    sp.setLanguage(Locale.UK);
                    sp.setSpeechRate(0.8f);
                }
            }
        });
        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isSpeaking) {
                    // Start speaking
                    String toSpeak = story.getText().toString();
                    sp.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                    isSpeaking = true;
                } else {
                    // Stop speaking
                    sp.stop();
                    isSpeaking = false;
                }
            }
        });
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Close the fragment
                sp.stop();
                isSpeaking = false;
                getParentFragmentManager().beginTransaction().remove(Story_popUp.this).commit();
            }
        });
        return v;
    }

}