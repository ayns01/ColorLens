package com.example.ayana.chekikkov1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class PhotoFilterActivity extends AppCompatActivity {
    Bitmap bmp;
    Bitmap frameImage;
    Bitmap mergedImages;
    ImageView mfinalImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_filter);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");

        Bundle extras = getIntent().getExtras();
        byte[] byteArray = extras.getByteArray(CropActivity.EXTRA_CROPPED_IMAGE);
        bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        mfinalImageView = findViewById(R.id.finalImageView);
//        mfinalImageView.setImageBitmap(bmp);

        frameImage = BitmapFactory.decodeResource(getResources(), R.drawable.frame_2x);
        mergedImages = createSingleImageFromMultipleImages(frameImage, bmp);

        mfinalImageView.setImageBitmap(mergedImages);

    }

    private Bitmap createSingleImageFromMultipleImages(Bitmap firstImage, Bitmap secondImage){

        Bitmap result = Bitmap.createBitmap(firstImage.getWidth(), firstImage.getHeight(), firstImage.getConfig());
        Bitmap s2 = Bitmap.createScaledBitmap(secondImage, 1300, 1300, false);
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(firstImage, 0f, 0f, null);
        canvas.drawBitmap(s2, 128, 150, null);
        return result;
    }
}
