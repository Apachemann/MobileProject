package com.example.snapnstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;

public class FillInTheBlankQuestion extends AppCompatActivity {

    // Declare initial variables
    TextView questionBox;
    String questionData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_in_the_blank_question);

        // Create a new fill-in-the-blank question when the activity is started
        createFillInTheBlankQuestion();
    }

    protected void createFillInTheBlankQuestion() {
        // Grab the object of the text field
        questionBox=findViewById(R.id.fillInQuestion);

        // Collect the data passed from the Question activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            questionData = extras.getString("ocrGenData");
            // Set the question text field to display the data
            questionBox.setText(questionData);

        }
    }
}