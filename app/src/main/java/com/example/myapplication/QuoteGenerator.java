package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class QuoteGenerator extends Fragment {
    String[] quotes = {
            "Be yourself; everyone else is already taken.",
            "So many books, so little time.",
            "A room without books is like a body without a soul.",
            "You only live once, but if you do it right, once is enough.",
            "Be the change that you wish to see in the world.",
            "",
            "A friend is someone who knows all about you and still loves you.",
            "",
            "To live is the rarest thing in the world. Most people exist, that is all.",
            "Always forgive your enemies; nothing annoys them so much.",
            "",
            "Without music, life would be a mistake.",
            "We accept the love we think we deserve.",
            "",
            "Live as if you were to die tomorrow. Learn as if you were to live forever.",
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.quote_generator, container, false);

        Button button = v.findViewById(R.id.myButton);
        TextView quote = v.findViewById(R.id.myquote);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int index = 0;
                do {
                    index = random.nextInt(quotes.length);
                } while (quotes[index] == null || quotes[index].isEmpty());
                quote.setText(quotes[index]);
            }
        });
        return v;
    }
}