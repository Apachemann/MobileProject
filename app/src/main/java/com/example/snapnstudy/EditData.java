package com.example.snapnstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditData extends AppCompatActivity implements View.OnClickListener{

    FloatingActionButton questionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        // Grab the object of the floating action button
        questionBtn=findViewById(R.id.floatingActionButton5);

        questionBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // Do something in response to floating action button
        if (view == questionBtn) {
            Intent intent = new Intent(this, Question.class);
            startActivity(intent);
        }
    }
}