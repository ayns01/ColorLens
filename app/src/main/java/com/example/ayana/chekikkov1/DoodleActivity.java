package com.example.ayana.chekikkov1;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class DoodleActivity extends AppCompatActivity {
    Bitmap bmp, frameImage, mergedImages;
    ImageView mfinalImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doodle);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");


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
