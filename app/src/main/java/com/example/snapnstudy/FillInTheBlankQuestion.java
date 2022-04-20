package com.example.snapnstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.Span;

public class FillInTheBlankQuestion extends AppCompatActivity {

    // Declare initial variables
    TextView questionBox;
    String questionData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_in_the_blank_question);

        // Create a new fill-in-the-blank question when the activity is started
        try {
            createFillInTheBlankQuestion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void createFillInTheBlankQuestion() throws Exception {
        // Grab the object of the text field
        questionBox = findViewById(R.id.fillInQuestion);

        // Collect the data passed from the Question activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            questionData = extras.getString("ocrGenData");
            // Set the question text field to display the data
            // questionBox.setText(questionData);
        }

        // 1. Collect all sentences from the data passed from the Question activity
        // Load sentence detector model
        InputStream inputStream;
        SentenceModel sentModel = null;
        String[] sentences = null;

        inputStream = getAssets().open("en-sent.bin");
        sentModel = new SentenceModel(inputStream);

        // Instantiating the SentenceDetectorME class
        if (sentModel != null) {
            SentenceDetectorME detector = new SentenceDetectorME(sentModel);

            //Detecting the sentence(s)
            sentences = detector.sentDetect(questionData);
        }

        // 2. Randomly choose one sentence
        Random random = new Random();
        int ranSentenceIndex = random.nextInt(sentences.length);
        String randomSentence = (sentences[ranSentenceIndex]);

        //questionBox.append(sentences[1]);

//        int arraySize = sentences.length;
//        for(int i = 0; i < arraySize; i++) {
//            questionBox.append(sentences[i]);
//        }
        // 3. Randomly choose one noun or verb from the sentence to be the blank
        // Load POS detector model
        POSModel partsModel = null;

        inputStream = getAssets().open("en-posmaxent.bin");
        partsModel = new POSModel(inputStream);

        // Causes Error //
        // java.lang.RuntimeException: Unable to start activity ComponentInfo{com.example.snapnstudy/com.example.snapnstudy.FillInTheBlankQuestion}: java.lang.IllegalStateException: javax.xml.parsers.ParserConfigurationException: http://javax.xml.XMLConstants/feature/secure-processing
        // Instantiating the POSTaggerME class
        POSTaggerME tagger = new POSTaggerME(partsModel);

        //Tokenizing the sentence using WhitespaceTokenizer class
        WhitespaceTokenizer whitespaceTokenizer= WhitespaceTokenizer.INSTANCE;
        String[] tokens = whitespaceTokenizer.tokenize(randomSentence);

        //Generating tags
        String[] tags = tagger.tag(tokens);

        //Instantiating the POSSample class
        POSSample sample = new POSSample(tokens, tags);
        questionBox.append(sample.toString());

        // 4. Replace the noun or verb in the sentence with _____ and the noun or verb is the answer
        // 5. Show the sentence with the blank in the text view
        // 6. When the user hits the submit button, check the edit text for the answer
        // 7. Show Correct/Incorrect toast message
        // 8. Allow the user to refresh for another randomized question

        }
    }