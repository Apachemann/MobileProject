package com.example.snapnstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.Locale;
import java.util.Random;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;

public class FillInTheBlankQuestion extends AppCompatActivity {

    // Declare initial variables
    Button submitAnswer;
    TextView questionBox;
    EditText answerBox;
    String questionData;
    String blankWord;
    String randomSentence;

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
        // Grab the object of the text field, edit text, and button
        questionBox = findViewById(R.id.fillInQuestion);
        answerBox = findViewById(R.id.editTextAnswer);
        submitAnswer = findViewById(R.id.submitBtn);

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
        randomSentence = (sentences[ranSentenceIndex]);

        //questionBox.append(sentences[1]);

//        int arraySize = sentences.length;
//        for(int i = 0; i < arraySize; i++) {
//            questionBox.append(sentences[i]);
//        }
        // 3. Choose one noun from the sentence to be the blank
        // Load POS detector model
        POSModel partsModel = null;

        inputStream = getAssets().open("en-pos-maxent.zip");
        partsModel = new POSModel(inputStream);

        // Causes system error when trying to load this activity //
        // java.lang.IllegalStateException: javax.xml.parsers.ParserConfigurationException: http://javax.xml.XMLConstants/feature/secure-processing
        // Instantiating the POSTaggerME class
        POSTaggerME tagger = new POSTaggerME(partsModel);

        //Tokenizing the sentence using WhitespaceTokenizer class
        WhitespaceTokenizer whitespaceTokenizer= WhitespaceTokenizer.INSTANCE;
        String[] tokens = whitespaceTokenizer.tokenize(randomSentence);

        //Generating tags
        String[] tags = tagger.tag(tokens);

        //Instantiating the POSSample class
        POSSample sample = new POSSample(tokens, tags);

        int indexEnd;
        int indexStart;
        char wordFinder = ' ';
        String [] wordPickerFromSent;

//        questionBox.append(sample.toString());

        if (sample.toString().contains("_NN")) {

            indexEnd = sample.toString().indexOf("_NN");
            indexStart = indexEnd;
            wordFinder = sample.toString().charAt(indexEnd);

            while (!Character.isWhitespace(wordFinder) && indexStart != 0) {
                wordFinder = sample.toString().charAt(indexStart);
                indexStart = indexStart - 1;
            }

//            String indexStartStr = Integer.toString(indexStart);
//            questionBox.append("\n" + indexStartStr);
//
//            String indexEndStr = Integer.toString(indexEnd);
//            questionBox.append("\n" + indexEndStr);

            if (indexStart == 0) {
                blankWord = sample.toString().substring(indexStart, indexEnd);
            } else {
                blankWord = sample.toString().substring(indexStart + 2, indexEnd);
            }
            // Remove any whitespace
            blankWord = blankWord.trim();

            // Remove punctuation
            blankWord = blankWord.replaceAll("\\p{Punct}","");

//            questionBox.append("\n" + blankWord);

            // Pick a random word in case there are no nouns
        } else {
            wordPickerFromSent = randomSentence.split(" ");
            Random randomWord = new Random();
            blankWord = wordPickerFromSent[randomWord.nextInt(wordPickerFromSent.length)];
            questionBox.append("This sentence doesn't contain anything useful!");
        }

        // 4. Replace the noun in the sentence with _____ and the noun is the answer
        String blanked = randomSentence.replaceAll(blankWord, "_____");

        // 5. Show the sentence with the blank in the text view
        questionBox.append(blanked);

        // 6. When the user hits the submit button, check the edit text for the answer
        submitAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userInput = answerBox.getText().toString().toLowerCase(Locale.ROOT);
                // 7. Show Correct/Incorrect toast message
                if(userInput.contains(blankWord.toLowerCase(Locale.ROOT))){
                    Toast.makeText(FillInTheBlankQuestion.this, "Your answer is correct!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FillInTheBlankQuestion.this, "Your answer is incorrect. Try again.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        // 8. Allow the user to refresh for another randomized question

        }
    }