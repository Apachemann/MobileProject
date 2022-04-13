package com.example.snapnstudy;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FixData extends AppCompatActivity {
    String ocrTextFinal;
    EditText ocrErrorFix;
    FloatingActionButton saveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_data);

        // Grab the object of the edit text field and floating action button
        ocrErrorFix=findViewById(R.id.editTextMultiLine);
        saveData=findViewById(R.id.saveData);


        // Collect the OCR data passed from MainActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ocrTextFinal = extras.getString("imageData");
            // Set the edit text field to display the OCR data
            ocrErrorFix.setText(ocrTextFinal);

        }

        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = ocrErrorFix.getText().toString();
                text.trim();
                MainActivity.addItem(text);
                finish();
            }
        });

    }

}