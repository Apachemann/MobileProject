package com.example.snapnstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button testBtn;
    FloatingActionButton addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Grab the objects of the button and floating action button
        testBtn=findViewById(R.id.test_edit_button1);
        addBtn=findViewById(R.id.floatingActionButton);

        testBtn.setOnClickListener(this);
        addBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // Do something in response to floating action button
        if (view==testBtn) {
            Intent intent = new Intent(this, EditData.class);
            startActivity(intent);
        }else if(view==addBtn) {
            Intent intent = new Intent(this, CreatePicture.class);
            startActivity(intent);
        }
    }
}