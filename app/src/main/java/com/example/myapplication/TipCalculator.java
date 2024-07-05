package com.example.myapplication;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.text.DecimalFormat;

public class TipCalculator extends Fragment {

    private EditText editTextBillAmount;
    private RadioGroup radioGroupTipPercentage;
    private Button buttonCalculate;
    private TextView textViewTipAmount;
    private TextView textViewTotalAmount;
    private EditText editTextCustomTip;

    private double billAmount = 0.0;
    private double tipPercentage = 0.0;

    public TipCalculator() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tip_calculator, container, false);

        editTextBillAmount = view.findViewById(R.id.editTextBillAmount);
        radioGroupTipPercentage = view.findViewById(R.id.radioGroupTipPercentage);
        buttonCalculate = view.findViewById(R.id.buttonCalculate);
        textViewTipAmount = view.findViewById(R.id.textViewTipAmount);
        textViewTotalAmount = view.findViewById(R.id.textViewTotalAmount);
        editTextCustomTip = view.findViewById(R.id.editTextCustomTip);

        radioGroupTipPercentage.check(R.id.radioButton10);
        tipPercentage = 10.0;
        editTextCustomTip.setVisibility(View.GONE);

        editTextBillAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Update billAmount when user inputs bill amount
                try {
                    billAmount = Double.parseDouble(s.toString());
                } catch (NumberFormatException e) {
                    billAmount = 0.0; // Reset to 0 if input is invalid
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Not needed
            }
        });

        radioGroupTipPercentage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButton10) {
                    tipPercentage = 10.0;
                    editTextCustomTip.setVisibility(View.GONE); // Hide EditText
                } else if (checkedId == R.id.radioButton15) {
                    tipPercentage = 15.0;
                    editTextCustomTip.setVisibility(View.GONE); // Hide EditText
                } else if (checkedId == R.id.radioButton20) {
                    tipPercentage = 20.0;
                    editTextCustomTip.setVisibility(View.GONE); // Hide EditText
                } else if (checkedId == R.id.radioButtonCustom) {
                    editTextCustomTip.setVisibility(View.VISIBLE); // Show EditText for custom input
                } else {
                    tipPercentage = 0.0;
                    editTextCustomTip.setVisibility(View.GONE); // Hide EditText by default
                }
            }
        });

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTipAndTotal();
            }
        });

        return view;
    }

    private void calculateTipAndTotal() {
        if (editTextCustomTip.getVisibility() == View.VISIBLE) {
            try {
                tipPercentage = Double.parseDouble(editTextCustomTip.getText().toString());
            } catch (NumberFormatException e) {
                tipPercentage = 0.0;
            }
        }

        double tipAmount = billAmount * (tipPercentage / 100);
        double totalAmount = billAmount + tipAmount;

        DecimalFormat df = new DecimalFormat("#.##"); // Format to two decimal places

        textViewTipAmount.setText("Tip Amount: $" + df.format(tipAmount));
        textViewTotalAmount.setText("Total Amount: $" + df.format(totalAmount));
    }
}
