package com.example.snapnstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Question extends AppCompatActivity implements View.OnClickListener{

    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        // Grab the object of the button
        submitBtn=findViewById(R.id.button);

        submitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // Do something in response to button
        if (view == submitBtn) {
            //Intent intent = new Intent(this, QuestionResult.class);
            //startActivity(intent);
        }
    }
}