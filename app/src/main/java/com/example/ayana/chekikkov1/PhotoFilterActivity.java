package com.example.ayana.chekikkov1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ayana.chekikkov1.Adapter.TabPageAdapter;

public class PhotoFilterActivity extends AppCompatActivity implements ColorsFragment.OnFragmentInteractionListener, DoodleFragment.OnFragmentInteractionListener, FramesFragment.OnFragmentInteractionListener {
    Bitmap bmp;
    Bitmap frameImage;
    Bitmap mergedImages;
    ImageView mfinalImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_filter);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");

        Bundle extras = getIntent().getExtras();
        byte[] byteArray = extras.getByteArray(CropActivity.EXTRA_CROPPED_IMAGE);
        bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        mfinalImageView = findViewById(R.id.finalImageView);
//        mfinalImageView.setImageBitmap(bmp);

        frameImage = BitmapFactory.decodeResource(getResources(), R.drawable.frame_2x);
        mergedImages = createSingleImageFromMultipleImages(frameImage, bmp);

        mfinalImageView.setImageBitmap(mergedImages);

        final TabLayout tabLayout = findViewById(R.id.tablayout);
//        TabItem tabColors = findViewById(R.id.tabColors);
//        TabItem tabFrames = findViewById(R.id.tabFrames);
//        TabItem tabDoodle = findViewById(R.id.tabDoodle);
        final ViewPager viewPager = findViewById(R.id.viewPager);

        TabPageAdapter tabPageAdapter = new TabPageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(tabPageAdapter);
        // Enable to sync  with tabs indicator
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
//                    toast("Position 1");
                } else if (tab.getPosition() == 1) {
//                    toast("Position 2");
                } else {
//                    toast("Position 3");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private Bitmap createSingleImageFromMultipleImages(Bitmap firstImage, Bitmap secondImage){

        Bitmap result = Bitmap.createBitmap(firstImage.getWidth(), firstImage.getHeight(), firstImage.getConfig());
        Bitmap s2 = Bitmap.createScaledBitmap(secondImage, 1300, 1300, false);
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(firstImage, 0f, 0f, null);
        canvas.drawBitmap(s2, 128, 150, null);
        return result;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
