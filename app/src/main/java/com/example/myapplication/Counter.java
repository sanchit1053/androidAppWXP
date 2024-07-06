package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class Counter extends Fragment {

    // Initialize views
    private TextView textArea;
    private Button incrementButton;
    private Button decreaseButton;
    private Button resetButton;
    private int count = 0;
    private static final String COUNT_KEY = "count_key";
    private TextInputLayout textInputLayout;
    private EditText textInputEditText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_counter, container, false);

        // Initialize views
        textArea = view.findViewById(R.id.counterValue);
        incrementButton = view.findViewById(R.id.buttonIncrease);
        decreaseButton = view.findViewById(R.id.buttonDecrease);
        resetButton = view.findViewById(R.id.buttonReset);

        // Getting input field
        textInputLayout = view.findViewById(R.id.textInputLayout);
        // Get the TextInputEditText inside TextInputLayout
        textInputEditText = textInputLayout.getEditText();


        // Set click listeners for buttons
        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Hide the keyboard
                hideKeyboard(incrementButton);

                // Get the current input value and update the text area
                int x = getInputValue();
                count += x;
                textArea.setText(String.format(String.valueOf(count)));

            }
        });

        decreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Hide the keyboard
                hideKeyboard(decreaseButton);

                // Get the current input value and update the text area
                int x = getInputValue();
                count -= x;
                textArea.setText(String.format(String.valueOf(count)));
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(resetButton);
                count = 0;
                textInputEditText.setText("1");
                textArea.setText("Start again!");
            }
        });

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt(COUNT_KEY, 0);
            textArea.setText(String.valueOf(count));
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(COUNT_KEY, count);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            count = savedInstanceState.getInt(COUNT_KEY, 0);
            textArea.setText(String.valueOf(count));
        }
    }


    // Method to get the input value from the EditText, defaulting to 1 if empty
    private int getInputValue(){
        String curr_input = textInputEditText.getText().toString().trim();
        int value;
        if(curr_input.isEmpty()){
            value = 1;
            textInputEditText.setText("1");
        }
        else {
            try {
                value = Integer.parseInt(curr_input);
            } catch (NumberFormatException e) {
                value = 1; // Default to 1 if parsing fails (non-numeric input)
                textInputEditText.setText("1");
            }
        }
        return value;
    }

    // Method to hide the soft keyboard
    private void hideKeyboard(Button btn) {
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(btn.getWindowToken(), 0);
        }
        textInputEditText.clearFocus();
    }
}