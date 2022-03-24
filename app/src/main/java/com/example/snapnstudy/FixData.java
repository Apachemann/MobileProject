package com.example.snapnstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FixData extends AppCompatActivity implements View.OnClickListener{

    String ocrTextFinal;
    EditText ocrErrorFix;
    FloatingActionButton saveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_data);

        // Grab the object of the edit text field and floating action button
        ocrErrorFix=findViewById(R.id.editTextMultiLine);
        saveData=findViewById(R.id.floatingActionButton4);

        saveData.setOnClickListener(this);

        // Collect the OCR data passed from MainActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ocrTextFinal = extras.getString("imageData");

            // Set the edit text field to display the OCR data
            ocrErrorFix.setText(ocrTextFinal);
        }
    }

    @Override
    public void onClick(View view) {
        // Do something in response to floating action button
        if (view==saveData) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}