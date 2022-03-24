package com.example.snapnstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class FixData extends AppCompatActivity {

    String ocrTextFinal;
    EditText ocrErrorFix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_data);

        // Grab the object of the edit text field
        ocrErrorFix=findViewById(R.id.editTextMultiLine);

        // Collect the OCR data passed from MainActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ocrTextFinal = extras.getString("imageData");

            // Set the edit text field to display the OCR data
            ocrErrorFix.setText(ocrTextFinal);
        }
    }


}