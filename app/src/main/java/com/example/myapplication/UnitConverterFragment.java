package com.example.myapplication;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UnitConverterFragment  extends Fragment {


    public UnitConverterFragment(){
        super(R.layout.unit_converter_fragment);
    }

    private enum UnitType {
        Distance,
        Temperature
    }

    private static class Unit {
        String name;
        UnitType unitType;
        Function<Double, Double> toStandard;
        Function<Double, Double> fromStandard;

        public Unit(String name, UnitType unitType, Function<Double, Double> toStandard, Function<Double, Double> fromStandard) {
            this.name = name;
            this.unitType = unitType;
            this.toStandard = toStandard;
            this.fromStandard = fromStandard;
        }

        public String getName() {
            return name;
        }

        public UnitType getUnitType() {
            return unitType;
        }
    }

    final private List<Unit> units = Arrays.asList(
            new Unit("kilometer", UnitType.Distance, id -> id * 1000, id -> id / 1000),
            new Unit("meter", UnitType.Distance, id -> id, id -> id),
            new Unit("centimeter", UnitType.Distance, id -> id / 100, id -> id * 100),
            new Unit("millimeter", UnitType.Distance, id -> id / 1000, id -> id * 1000),
            new Unit("Celsius", UnitType.Temperature, id -> id, id -> id),
            new Unit("Fahrenheit", UnitType.Temperature, id -> 5.0 / 9.0 * (id - 32), id -> 9.0 / 5.0 * id + 32),
            new Unit("Kelvin", UnitType.Temperature, id -> id + 273, id -> id - 273));

    private String convert(String input, String from, String to) {
        Unit getCurrent = units.stream().filter(item -> item.getName().equals(from)).findFirst().get();
        Unit needed = units.stream().filter(item -> item.getName().equals(to)).findFirst().get();
        if (input.isEmpty() || !getCurrent.getUnitType().equals(needed.getUnitType())) {
            return "CANNOT CONVERT";
        }
        int given;

        try {
            given = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return "CANNOT PARSE INT";
        }

        return needed.fromStandard.apply(getCurrent.toStandard.apply((double) given)).toString();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.unit_converter_fragment, container, false);

//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.unit_converter_fragment);

        List<String> unit_names = units.stream().map(Unit::getName).collect(Collectors.toList());


        EditText editText = view.findViewById(R.id.textInput);
        Spinner fromSpinner = view.findViewById(R.id.fromSpinner);
        Spinner toSpinner = view.findViewById(R.id.toSpinner);
        TextView output = view.findViewById(R.id.resultTextView);

        // Set initial content descriptions
        editText.requestFocus();
        editText.setContentDescription(getString(R.string.enter_a_number_hint));
        fromSpinner.setContentDescription(getString(R.string.from_unit_spinner));
        toSpinner.setContentDescription(getString(R.string.to_unit_spinner));

        editText.setText("1");

        // FROM https://www.geeksforgeeks.org/spinner-in-android-using-java-with-example/
        //////////////////////////////////////////////////////////////////////////

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                view.getContext(),
                android.R.layout.simple_spinner_item,
                unit_names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);

        //////////////////////////////////////////////////////////////////////////


        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String result = convert(editText.getText().toString(), fromSpinner.getSelectedItem().toString(), toSpinner.getSelectedItem().toString());
                output.setText(result);
                output.announceForAccessibility("Result is " + result);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String result = convert(editText.getText().toString(), fromSpinner.getSelectedItem().toString(), toSpinner.getSelectedItem().toString());
                output.setText(result);
                output.announceForAccessibility("Result is " + result);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i0, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i0, int i2) {
                String result = convert(editText.getText().toString(), fromSpinner.getSelectedItem().toString(), toSpinner.getSelectedItem().toString());
                output.setText(result);
                output.announceForAccessibility("Result is " + result);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;

    }
}
