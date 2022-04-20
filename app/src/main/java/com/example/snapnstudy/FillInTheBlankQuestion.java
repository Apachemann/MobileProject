package com.example.snapnstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class FillInTheBlankQuestion extends AppCompatActivity {

    // Declare initial variables
    TextView questionBox;
    String questionData;

    //Loading sentence detector model
    InputStream inputStream = null;
    SentenceModel model = null;

        try {

        inputStream = getAssets().open("en-sent.bin");
        model = new SentenceModel(inputStream);

    } catch (IOException e) {
        e.printStackTrace();
    }

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

        // 1. Collect all sentences from the data passed from the Question activity
        // 2. Randomly choose one sentence
        // 3. Randomly choose one noun or verb from the sentence to be the blank
        // 4. Replace the noun or verb in the sentence with _____ and the noun or verb is the answer
        // 5. Show the sentence with the blank in the text view
        // 6. When the user hits the submit button, check the edit text for the answer
        // 7. Show Correct/Incorrect toast message
        // 8. Allow the user to refresh for another randomized question
    }
}