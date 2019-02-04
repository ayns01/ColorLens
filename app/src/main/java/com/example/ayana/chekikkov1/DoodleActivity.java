package com.example.ayana.chekikkov1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.ImageView;

import com.example.ayana.chekikkov1.Adapter.DoodleAdapter;
import com.example.ayana.chekikkov1.Paint.PaintView;
import com.example.ayana.chekikkov1.Utils.SpacesItemDecoration;

public class DoodleActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DoodleAdapter mDoodleAdapter;
    ImageView mMergedImageView;
    Bitmap bmp;
    Bitmap bmp2;
    Bitmap newbmp;

    PaintView mPaintView;

    int[] paletteList = {R.color.black, R.color.pink, R.color.yellow, R.color.green,
            R.color.blue, R.color.white};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doodle_to_image);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");

        mMergedImageView = findViewById(R.id.doodleImageView);

        mPaintView = findViewById(R.id.paintView);

        Bundle extras = getIntent().getExtras();
        byte[] byteArray = extras.getByteArray(PhotoFilterActivity.EXTRA_MERGED_IMAGE);
        bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        newbmp = Bitmap.createScaledBitmap(bmp , 1300, 1300, false);

        mMergedImageView.setImageBitmap(newbmp);
        bmp2 = BitmapFactory.decodeResource(getResources(), R.drawable.backstreetboy);

        recyclerView = findViewById(R.id.doodle_recyclerview);
        mDoodleAdapter = new DoodleAdapter(this, paletteList);

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

        mPaintView.init(newbmp);
    }
}
