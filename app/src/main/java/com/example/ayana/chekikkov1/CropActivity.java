package com.example.ayana.chekikkov1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.isseiaoki.simplecropview.CropImageView;

import java.io.ByteArrayOutputStream;

public class CropActivity extends AppCompatActivity {
    Bitmap bmp;
    Bitmap croppedBitmap;
    CropImageView mCropView;
    public static final String EXTRA_CROPPED_IMAGE = "com.example.ayana.chekikkov1.extra.CROPPED.IMAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop);

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
            croppedBitmap = mCropView.getCroppedBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            croppedBitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
            byte[] byteArray = stream.toByteArray();
            Intent intent = new Intent(CropActivity.this, PhotoFilterActivity.class);
            intent.putExtra(EXTRA_CROPPED_IMAGE, byteArray);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
