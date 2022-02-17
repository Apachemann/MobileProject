package com.example.snapnstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ResizePicture extends AppCompatActivity implements View.OnClickListener{

    FloatingActionButton resizeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resize_picture);

        // Grab the object of the floating action button
        resizeBtn=findViewById(R.id.floatingActionButton3);

        resizeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // Do something in response to floating action button
        if (view == resizeBtn) {
            Intent intent = new Intent(this, FixData.class);
            startActivity(intent);
        }
    }
}