package com.example.ayana.chekikkov1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
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

import java.io.OutputStream;

public class DoodleActivity extends AppCompatActivity {
    private static final int REQUEST_SAVE_IMAGE = 1002;
    RecyclerView recyclerView;
    DoodleAdapter mDoodleAdapter;
    Bitmap bmp;
    Bitmap frameBmp;
    Bitmap paintBitmap;

    ImageView mPhotoView;
    ImageView mFrameView;
    PaintView mPaintView;

    Bitmap result;

    int[] paletteList = {R.color.black, R.color.deep_koamaru, R.color.pastel_blue, R.color.lavender_gray,
            R.color.queen_pink, R.color.orange_yellow, R.color.white};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doodle_to_image);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");

//        mPhotoView = findViewById(R.id.doodleImageView);
//        mFrameView = findViewById(R.id.doodleFrameView);
//
//        mPaintView = findViewById(R.id.paintView);

//
//        Bundle extras = getIntent().getExtras();
//        byte[] byteArray = extras.getByteArray(PhotoFilterActivity.EXTRA_PHOTO_IMAGE);
//        bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//
//        int frameId = extras.getInt(PhotoFilterActivity.EXTRA_FRAME_IMAGE);
//
//        mPhotoView.setImageBitmap(bmp);
//        mFrameView.setImageResource(frameId);

//
//        recyclerView = findViewById(R.id.doodle_recyclerview);
//        mDoodleAdapter = new DoodleAdapter(this, paletteList, this);
//
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(
//                this,
//                LinearLayoutManager.HORIZONTAL,
//                false);
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        int space = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12,
//                getResources().getDisplayMetrics());
//        recyclerView.addItemDecoration(new SpacesItemDecoration(space));
//        recyclerView.setAdapter(mDoodleAdapter);
//
//        frameBmp = BitmapFactory.decodeResource(getResources(), frameId);
//        paintBitmap = Bitmap.createBitmap(frameBmp.getWidth(), frameBmp.getHeight(), Bitmap.Config.ARGB_8888);
//        mPaintView.init(paintBitmap, 0x00, 0x00, 0x00);
    }

//    @Override
//    public void onPaletteColorChange(int pos) {
//        switch (pos) {
//            case 0:
//                // black
//                mPaintView.init(paintBitmap, 0x16, 0x16, 0x16);
//                break;
//            case 1:
//                // deep_koamaru
//                mPaintView.init(paintBitmap, 35, 54, 104);
//                break;
//            case 2:
//                // pastel_blue
//                mPaintView.init(paintBitmap, 160, 195, 210);
//                break;
//            case 3:
//                // lavender_gray
//                mPaintView.init(paintBitmap, 190, 190, 209);
//                break;
//            case 4:
//                // queen_pink
//                mPaintView.init(paintBitmap, 248, 205, 210);
//                break;
//            case 5:
//                // orange_yellow
//                mPaintView.init(paintBitmap, 249, 200, 99);
//                break;
//            case 6:
//                // white
//                mPaintView.init(paintBitmap, 255, 255, 255);
//                break;
//            default:
//                return;
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_save) {
            result = Bitmap.createBitmap(frameBmp.getWidth(), frameBmp.getHeight(), frameBmp.getConfig());
            Bitmap paintBmp = Bitmap.createScaledBitmap(paintBitmap, paintBitmap.getWidth(), paintBitmap.getHeight(), false);
            Bitmap s2 = Bitmap.createScaledBitmap(bmp, 1300, 1300, false);
            Canvas canvas = new Canvas(result);
            canvas.drawBitmap(frameBmp, 0f, 0f, null);
            canvas.drawBitmap(s2, 128, 150, null);
            canvas.drawBitmap(paintBmp, 0, 0, null);

            Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
            intent.setType("image/jpeg");
            intent.putExtra(Intent.EXTRA_TITLE, System.currentTimeMillis() + "awesome-photo.jpeg");
            startActivityForResult(intent, REQUEST_SAVE_IMAGE);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {

        if (requestCode == REQUEST_SAVE_IMAGE && resultCode == Activity.RESULT_OK) {
            if(resultData.getData() != null){

                Uri uri = resultData.getData();

                try(OutputStream outputStream = getContentResolver().openOutputStream(uri)) {
                    result.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

}
