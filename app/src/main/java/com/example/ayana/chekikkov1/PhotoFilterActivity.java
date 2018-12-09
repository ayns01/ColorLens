package com.example.ayana.chekikkov1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(firstImage, 0f, 0f, null);
        canvas.drawBitmap(secondImage, 25, 25, null);
        return result;
    }
}
