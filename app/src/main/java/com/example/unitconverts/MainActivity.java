package com.example.unitconverts;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerCategories, spinnerFrom, spinnerTo;
    private RadioGroup radioGroupFromTo;
    private RadioButton radioButtonFrom, radioButtonTo;
    private EditText editTextValue;
    private Button buttonConvert;
    private TextView textViewResult;

    private String[] categories = {"Length", "Weight", "Temperature", "Height"};
    private String[] lengthUnits = {"Centimeters", "Meters", "Inches", "Feet"};
    private String[] weightUnits = {"Grams", "Kilograms", "Ounces", "Pounds"};
    private String[] temperatureUnits = {"Celsius", "Fahrenheit"};
    private String[] heightUnits = {"Centimeters", "Meters", "Inches", "Feet"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerCategories = findViewById(R.id.spinnerCategories);
        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);
        radioGroupFromTo = findViewById(R.id.radioGroupFromTo);
        radioButtonFrom = findViewById(R.id.radioButtonFrom);
        radioButtonTo = findViewById(R.id.radioButtonTo);
        editTextValue = findViewById(R.id.editTextValue);
        buttonConvert = findViewById(R.id.buttonConvert);
        textViewResult = findViewById(R.id.textViewResult);

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategories.setAdapter(categoryAdapter);

        spinnerCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                updateUnitSpinners(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertUnit();
            }
        });
    }

    private void updateUnitSpinners(int position) {
        String[] units;

        switch (position) {
            case 0:
                units = lengthUnits;
                break;
            case 1:
                units = weightUnits;
                break;
            case 2:
                units = temperatureUnits;
                break;
            case 3:
                units = heightUnits;
                break;
            default:
                units = lengthUnits;
                break;
        }

        ArrayAdapter<String> unitAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(unitAdapter);
        spinnerTo.setAdapter(unitAdapter);
    }

    private void convertUnit() {
        String category = spinnerCategories.getSelectedItem().toString();
        String fromUnit = spinnerFrom.getSelectedItem().toString();
        String toUnit = spinnerTo.getSelectedItem().toString();

        if (fromUnit.equals(toUnit)) {
            Toast.makeText(this, "Select different units for conversion.", Toast.LENGTH_SHORT).show();
            return;
        }

        double value;
        try {
            value = Double.parseDouble(editTextValue.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Enter a valid number.", Toast.LENGTH_SHORT).show();
            return;
        }

        double result;

        switch (category) {
            case "Length":
                result = convertLength(value, fromUnit, toUnit);
                break;
            case "Weight":
                result = convertWeight(value, fromUnit, toUnit);
                break;
            case "Temperature":
                result = convertTemperature(value, fromUnit, toUnit);
                break;
            case "Height":
                result = convertHeight(value, fromUnit, toUnit);
                break;
            default:
                result = 0;
                break;
        }

        textViewResult.setText(String.format("%.2f %s = %.2f %s", value, fromUnit, result, toUnit));
    }

    private double convertLength(double value, String fromUnit, String toUnit) {
        // Conversion formulas for length units
        double convertedValue = 0;

        switch (fromUnit) {
            case "Centimeters":
                switch (toUnit) {
                    case "Meters":
                        convertedValue = value / 100.0;
                        break;
                    case "Inches":
                        convertedValue = value / 2.54;
                        break;
                    case "Feet":
                        convertedValue = value / 30.48;
                        break;
                }
                break;
            case "Meters":
                switch (toUnit) {
                    case "Centimeters":
                        convertedValue = value * 100.0;
                        break;
                    case "Inches":
                        convertedValue = value * 39.37;
                        break;
                    case "Feet":
                        convertedValue = value * 3.28084;
                        break;
                }
                break;
            case "Inches":
                switch (toUnit) {
                    case "Centimeters":
                        convertedValue = value * 2.54;
                        break;
                    case "Meters":
                        convertedValue = value * 0.0254;
                        break;
                    case "Feet":
                        convertedValue = value * 0.0833333;
                        break;
                }
                break;
            case "Feet":
                switch (toUnit) {
                    case "Centimeters":
                        convertedValue = value * 30.48;
                        break;
                    case "Meters":
                        convertedValue = value * 0.3048;
                        break;
                    case "Inches":
                        convertedValue = value * 12.0;
                        break;
                }
                break;
        }

        return convertedValue;
    }

    private double convertWeight(double value, String fromUnit, String toUnit) {
        // Conversion formulas for weight units
        double convertedValue = 0;

        switch (fromUnit) {
            case "Grams":
                switch (toUnit) {
                    case "Kilograms":
                        convertedValue = value / 1000.0;
                        break;
                    case "Ounces":
                        convertedValue = value / 28.3495;
                        break;
                    case "Pounds":
                        convertedValue = value / 453.592;
                        break;
                }
                break;
            case "Kilograms":
                switch (toUnit) {
                    case "Grams":
                        convertedValue = value * 1000.0;
                        break;
                    case "Ounces":
                        convertedValue = value * 35.27396;
                        break;
                    case "Pounds":
                        convertedValue = value * 2.20462;
                        break;
                }
                break;
            case "Ounces":
                switch (toUnit) {
                    case "Grams":
                        convertedValue = value * 28.3495;
                        break;
                    case "Kilograms":
                        convertedValue = value * 0.0283495;
                        break;
                    case "Pounds":
                        convertedValue = value / 16.0;
                        break;
                }
                break;
            case "Pounds":
                switch (toUnit) {
                    case "Grams":
                        convertedValue = value * 453.592;
                        break;
                    case "Kilograms":
                        convertedValue = value * 0.453592;
                        break;
                    case "Ounces":
                        convertedValue = value * 16.0;
                        break;
                }
                break;
        }

        return convertedValue;
    }

    private double convertTemperature(double value, String fromUnit, String toUnit) {
        // Conversion formulas for temperature units
        double convertedValue = 0;

        if (fromUnit.equals("Celsius") && toUnit.equals("Fahrenheit")) {
            convertedValue = (value * 9 / 5) + 32;
        } else if (fromUnit.equals("Fahrenheit") && toUnit.equals("Celsius")) {
            convertedValue = (value - 32) * 5 / 9;
        }

        return convertedValue;
    }

    private double convertHeight(double value, String fromUnit, String toUnit) {
        // Conversion formulas for height units
        double convertedValue = 0;

        switch (fromUnit) {
            case "Centimeters":
                switch (toUnit) {
                    case "Meters":
                        convertedValue = value / 100.0;
                        break;
                    case "Inches":
                        convertedValue = value / 2.54;
                        break;
                    case "Feet":
                        convertedValue = value / 30.48;
                        break;
                }
                break;
            case "Meters":
                switch (toUnit) {
                    case "Centimeters":
                        convertedValue = value * 100.0;
                        break;
                    case "Inches":
                        convertedValue = value * 39.37;
                        break;
                    case "Feet":
                        convertedValue = value * 3.28084;
                        break;
                }
                break;
            case "Inches":
                switch (toUnit) {
                    case "Centimeters":
                        convertedValue = value * 2.54;
                        break;
                    case "Meters":
                        convertedValue = value * 0.0254;
                        break;
                    case "Feet":
                        convertedValue = value * 0.0833333;
                        break;
                }
                break;
            case "Feet":
                switch (toUnit) {
                    case "Centimeters":
                        convertedValue = value * 30.48;
                        break;
                    case "Meters":
                        convertedValue = value * 0.3048;
                        break;
                    case "Inches":
                        convertedValue = value * 12.0;
                        break;
                }
                break;
        }

        return convertedValue;
    }
}
