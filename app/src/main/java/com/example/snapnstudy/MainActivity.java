package com.example.snapnstudy;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    // Declare initial variables
    FloatingActionButton addBtn;
    Button infoBtn;
    ActivityResultLauncher<Intent> activityResultLauncher;
    private static final int REQUEST_CAMERA_CODE = 100;
    @SuppressLint("StaticFieldLeak")
    static ListView listView;
    static ArrayList<String> items;
    @SuppressLint("StaticFieldLeak")
    static ListViewAdapter adapter;
    ImageButton capture_picture;
    String text, name;
    private String currentPhotoPath;
//  EditText input;
//  ImageView enter;

    // (OCR) Create an instance of TextRecognizer
    TextRecognizer recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Grab the objects of the button, floating action button, and image view
        addBtn=findViewById(R.id.floatingActionButton);
        infoBtn=findViewById(R.id.infoButton);

        addBtn.setOnClickListener(this);
        infoBtn.setOnClickListener(this);

        // Ask for camera permissions when the app starts
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {
               Manifest.permission.CAMERA
            }, REQUEST_CAMERA_CODE);
        }

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bundle bundle = result.getData().getExtras();
                    Bitmap finalPhoto = (Bitmap) bundle.get("data");

                    // (OCR) Prepare the input image
                    InputImage image = InputImage.fromBitmap(finalPhoto, 90);

                    // (OCR) Process the image
                    Task<Text> processImage =
                            recognizer.process(image)
                                    .addOnSuccessListener(new OnSuccessListener<Text>() {
                                        @Override
                                        public void onSuccess(Text visionText) {
                                            // Task completed successfully
                                            // ...
                                            // (OCR) Extract text from blocks of recognized text
                                            String imageText = visionText.getText();

                                            // Start and pass the recognized text to the FixData activity
                                            Intent intent = new Intent(MainActivity.this, FixData.class);
                                            intent.putExtra("imageData", imageText);
                                            startActivity(intent);
                                        }
                                    })
                                    .addOnFailureListener(
                                            new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    // Task failed with an exception
                                                    // ...
                                                    Toast.makeText(MainActivity.this, "OCR failed with an exception.",
                                                            Toast.LENGTH_SHORT).show();
                                                }
                                            });

                }
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intent);
                if(intent.resolveActivity(getPackageManager()) != null) {
                    activityResultLauncher.launch(intent);
                } else {
                    Toast.makeText(MainActivity.this, "The camera is not working.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onClick(View view) {
        // Do something in response to floating action button
        if (view==infoBtn) {
            Intent intent = new Intent(this, Information.class);
            startActivity(intent);
        }

    }
}