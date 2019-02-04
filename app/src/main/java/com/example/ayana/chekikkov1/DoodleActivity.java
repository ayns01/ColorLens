package com.example.ayana.chekikkov1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.ayana.chekikkov1.Adapter.DoodleAdapter;
import com.example.ayana.chekikkov1.Paint.PaintView;
import com.example.ayana.chekikkov1.Utils.SpacesItemDecoration;

import java.io.ByteArrayOutputStream;

public class DoodleActivity extends AppCompatActivity implements RecyclerPaletteClick {
    RecyclerView recyclerView;
    DoodleAdapter mDoodleAdapter;
    Bitmap bmp;
    Bitmap frameBmp;
    Bitmap paintBitmap;

    ImageView mPhotoView;
    ImageView mFrameView;
    PaintView mPaintView;

    int[] paletteList = {R.color.black, R.color.deep_koamaru, R.color.pastel_blue, R.color.lavender_gray,
            R.color.queen_pink, R.color.orange_yellow, R.color.white};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doodle_to_image);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");

        mPhotoView = findViewById(R.id.doodleImageView);
        mFrameView = findViewById(R.id.doodleFrameView);

        mPaintView = findViewById(R.id.paintView);

        Bundle extras = getIntent().getExtras();
        byte[] byteArray = extras.getByteArray(PhotoFilterActivity.EXTRA_PHOTO_IMAGE);
        bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        int frameId = extras.getInt(PhotoFilterActivity.EXTRA_FRAME_IMAGE);

        mPhotoView.setImageBitmap(bmp);
        mFrameView.setImageResource(frameId);

        recyclerView = findViewById(R.id.doodle_recyclerview);
        mDoodleAdapter = new DoodleAdapter(this, paletteList, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        int space = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12,
                getResources().getDisplayMetrics());
        recyclerView.addItemDecoration(new SpacesItemDecoration(space));
        recyclerView.setAdapter(mDoodleAdapter);

        frameBmp = BitmapFactory.decodeResource(getResources(), frameId);
        paintBitmap = Bitmap.createBitmap(frameBmp.getWidth(), frameBmp.getHeight(), Bitmap.Config.ARGB_8888);
        mPaintView.init(paintBitmap, 0x00, 0x00, 0x00);
    }

    @Override
    public void onPaletteColorChange(int pos) {
        switch (pos) {
            case 0:
                // black
                mPaintView.init(paintBitmap, 0x16, 0x16, 0x16);
                break;
            case 1:
                // deep_koamaru
                mPaintView.init(paintBitmap, 35, 54, 104);
                break;
            case 2:
                // pastel_blue
                mPaintView.init(paintBitmap, 160, 195, 210);
                break;
            case 3:
                // lavender_gray
                mPaintView.init(paintBitmap, 190, 190, 209);
                break;
            case 4:
                // queen_pink
                mPaintView.init(paintBitmap, 248, 205, 210);
                break;
            case 5:
                // orange_yellow
                mPaintView.init(paintBitmap, 249, 200, 99);
                break;
            case 6:
                // white
                mPaintView.init(paintBitmap, 255, 255, 255);
                break;
            default:
                return;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

//        if (id == R.id.action_go_to_doodle) {
//            Bitmap photoBmp = testBitmap;
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            photoBmp.compress(Bitmap.CompressFormat.JPEG, 50, stream);
//            byte[] byteArray = stream.toByteArray();
//            Intent intent = new Intent(this, DoodleActivity.class);
//            intent.putExtra(EXTRA_PHOTO_IMAGE, byteArray);
//            intent.putExtra(EXTRA_FRAME_IMAGE, currentId);
//            startActivity(intent);
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }
}
