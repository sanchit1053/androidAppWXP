package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class QuoteGenerator extends Fragment {
    String[] quotes = new String[]{
//            getString(R.string.story1),
//            getString(R.string.story2),
//            getString(R.string.story3),
//            "",
//            getString(R.string.story4),
//            "",
//            getString(R.string.story5),
            "Once Buddha was walking from one town to another town with a few of his followers. This was in the initial days. While\n" +
                    "    they were traveling, they happened to pass a lake. They stopped there and Buddha told one of his disciples, “I am\n" +
                    "    thirsty. Please get me some water from that lake there”. The disciple walked up to the lake. When he reached it,\n" +
                    "    he noticed that some people were washing clothes in the water and, right at that moment, a bullock cart started\n" +
                    "    crossing the lake right at the edge of it. As a result, the water became very muddy, very turbid. The disciple thought,\n" +
                    "    “How can I give this muddy water to Buddha to drink?!” So he came back and told the Buddha, “The water in there is very\n" +
                    "    muddy. I don’t think it is fit to drink”.So, the Buddha said, let us take a little rest here by the tree. After about\n" +
                    "    half an hour, again Buddha asked the same disciple to go back to the lake and get him some water to drink. The disciple\n" +
                    "    obediently went back to the lake. This time he found that the lake had absolutely clear water in it. The mud had settled\n" +
                    "    down and the water above it looked fit to be had. So he collected some water in a pot and brought it to the Buddha.\n" +
                    "    The Buddha looked at the water, and then he looked up at the disciple and said, “See, You let the water be and the mud\n" +
                    "    settled down on its own. You got a clear water. It didn’t require any effort”.\n" +
                    "    Moral: Your mind is also like that. When it is disturbed, just let it be. Give it a little time. It will settle down on "

    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.quote_generator, container, false);

        Button button = v.findViewById(R.id.myButton);
        TextView quote = v.findViewById(R.id.myquote);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int index;
                do {
                    index = random.nextInt(quotes.length);
                } while (quotes[index] == null || quotes[index].isEmpty());
                quote.setText(quotes[index]);
            }
        });

        quote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Quote_Pop_up_fragment pop_fragment = new Quote_Pop_up_fragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null).add(R.id.fragmentContainerViewQuote, pop_fragment).commit();
            }
+        });

        return v;
    }

}