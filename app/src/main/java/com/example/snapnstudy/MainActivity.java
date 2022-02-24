package com.example.snapnstudy;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button testBtn;
    FloatingActionButton addBtn;
    ImageView imagePreview;
    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Grab the objects of the button, floating action button, and image view
        testBtn=findViewById(R.id.test_edit_button1);
        addBtn=findViewById(R.id.floatingActionButton);
        imagePreview=findViewById(R.id.ivPreview);

        testBtn.setOnClickListener(this);
        addBtn.setOnClickListener(this);

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bundle bundle = result.getData().getExtras();
                    Bitmap finalPhoto = (Bitmap) bundle.get("data");
                    imagePreview.setImageBitmap(finalPhoto);
                }
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent.resolveActivity(getPackageManager()) != null) {
                    activityResultLauncher.launch(intent);
                } else {
                    Toast.makeText(MainActivity.this, "The camera is not working",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        // Do something in response to floating action button
        if (view==testBtn) {
            Intent intent = new Intent(this, EditData.class);
            startActivity(intent);
        }
    }
    
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == CAMERA_ACTION_CODE && resultCode == RESULT_OK && data != null) {
//            Bundle bundle = data.getExtras();
//            Bitmap finalPhoto = (Bitmap) bundle.get("data");
//            imagePreview.setImageBitmap(finalPhoto);
//        }
//    }
}