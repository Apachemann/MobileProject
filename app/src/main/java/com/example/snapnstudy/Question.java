package com.example.snapnstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Question extends AppCompatActivity {
    String TextFinal;
    TextView FixedText;
    Button fillBlankQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        // Grab the object of the edit text field and floating action button
        FixedText=findViewById(R.id.question);
        fillBlankQuestion=findViewById(R.id.fillBlankGenBtn);

        // Collect the OCR data passed from MainActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            TextFinal = extras.getString("questionData");

            // Set the edit text field to display the OCR data
            FixedText.setText(TextFinal);

        }

        fillBlankQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Take text from the question box to be used to generate a fill-in-the-blank question
                String fillQuestionGen = FixedText.getText().toString();
                fillQuestionGen.trim();
                Intent intent = new Intent(Question.this, FillInTheBlankQuestion.class);
                intent.putExtra("ocrGenData", fillQuestionGen);
                startActivity(intent);
            }
        });

    }

}