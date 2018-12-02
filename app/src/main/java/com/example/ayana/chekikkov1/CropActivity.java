package com.example.ayana.chekikkov1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.isseiaoki.simplecropview.CropImageView;
import com.isseiaoki.simplecropview.callback.CropCallback;

public class CropActivity extends AppCompatActivity {
    Bitmap bmp;
    CropImageView mCropView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Bundle extras = getIntent().getExtras();
        byte[] byteArray = extras.getByteArray(MainActivity.EXTRA_IMAGE);

        bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        mCropView = findViewById(R.id.cropImageView);
        mCropView.setCropMode(CropImageView.CropMode.SQUARE);
        mCropView.setInitialFrameScale(0.75f);
        mCropView.setGuideShowMode(CropImageView.ShowMode.SHOW_ON_TOUCH);
        mCropView.setImageBitmap(bmp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_next, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_next) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
