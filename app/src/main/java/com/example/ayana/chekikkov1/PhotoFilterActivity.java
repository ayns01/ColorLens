package com.example.ayana.chekikkov1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ayana.chekikkov1.Adapter.TabPageAdapter;
import com.example.ayana.chekikkov1.FilterImage.FilterToImage;

import java.io.ByteArrayOutputStream;

public class PhotoFilterActivity extends AppCompatActivity implements
                                                    ColorsFragment.OnFragmentInteractionListener,
                                                    FramesFragment.OnFragmentInteractionListener {
    Bitmap bmp;
    private Bitmap frameImage;
    private Bitmap mergedImages;
    ImageView mPreviewImageView;
    ImageView mPreviewFrameView;
    Bitmap testBitmap;

    private int currentId = R.drawable.frame_white_2x;

    public static final String EXTRA_MERGED_IMAGE = "com.example.ayana.chekikkov1.extra.MERGED.IMAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_filter);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");

        Bundle extras = getIntent().getExtras();
        byte[] byteArray = extras.getByteArray(CropActivity.EXTRA_CROPPED_IMAGE);
        bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        mPreviewImageView = findViewById(R.id.previewImageView);
        mPreviewFrameView = findViewById(R.id.previewFrameView);

        frameImage = BitmapFactory.decodeResource(getResources(), R.drawable.frame_white_2x);
//        mergedImages = createSingleImageFromMultipleImages(frameImage, bmp);

        mPreviewImageView.setImageBitmap(bmp);
        mPreviewFrameView.setImageBitmap(frameImage);

        final TabLayout tabLayout = findViewById(R.id.tablayout);
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
                } else if (tab.getPosition() == 1) {
                } else {
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

    private Bitmap createSingleImageFromMultipleImages(Bitmap firstImage, Bitmap secondImage) {

//        Bitmap result = Bitmap.createBitmap(firstImage.getWidth(), firstImage.getHeight(), firstImage.getConfig());
        Bitmap result = firstImage.copy(Bitmap.Config.ARGB_8888, true);
        Bitmap s2 = Bitmap.createScaledBitmap(secondImage, 1300, 1300, false);
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(firstImage, 0f, 0f, null);
        canvas.drawBitmap(s2, 128, 150, null);
        return result;
    }

    @Override
    // ColorsFragment
    public void onFragmentInteraction(int pos) {
//        ColorFilter colorFilter = new LightingColorFilter(Color.WHITE, Color.rgb(255, 64, 129));
//        mPreviewImageView.setColorFilter(colorFilter);
        switch (pos) {
            case 0:
                testBitmap = Bitmap.createBitmap(bmp.getWidth(),
                        bmp.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(testBitmap);
                Paint paint = new Paint();
                ColorMatrix originalMatrix = new FilterToImage().backToOriginal();
                paint.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(originalMatrix)));
//                ColorMatrix originalMatrix = new FilterToImage().backToOriginal();
                mPreviewImageView.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(originalMatrix)));

                canvas.drawBitmap(bmp, 0, 0, paint);

                break;
            case 1:
                testBitmap = Bitmap.createBitmap(bmp.getWidth(),
                        bmp.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas2 = new Canvas(testBitmap);
                Paint paint2 = new Paint();
                ColorMatrix redMatrix = new FilterToImage().applyRedFilter();
                paint2.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(redMatrix)));
//                ColorMatrix originalMatrix = new FilterToImage().backToOriginal();
                mPreviewImageView.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(redMatrix)));

                canvas2.drawBitmap(bmp, 0, 0, paint2);

//                ColorMatrix redMatrix = new FilterToImage().applyRedFilter();
//                mPreviewImageView.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(redMatrix)));
                break;
            case 2:
                ColorMatrix greenMatrix = new FilterToImage().applyOrangeFilter();
                mPreviewImageView.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(greenMatrix)));
                break;
            case 3:
                ColorMatrix blueMatrix = new FilterToImage().applyBlueFilter();
                mPreviewImageView.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(blueMatrix)));
                break;
            case 4:
                ColorFilter duoColor = new FilterToImage().duotoneColorFilter(Color.LTGRAY, Color.YELLOW, 1.3f);
                mPreviewImageView.setColorFilter(duoColor);
                break;
            case 5:
                ColorFilter duoChinaColor = new FilterToImage().duotoneColorFilter(Color.BLACK, Color.WHITE, 1.3f);
                mPreviewImageView.setColorFilter(duoChinaColor);
                break;
            default:
                return;
        }
    }

    @Override
    // Frames Fragment
    public void onFramesFragmentInteraction(int pos) {
        switch (pos) {
            case 0:
                mPreviewFrameView.setImageResource(R.drawable.frame_white_2x);
                currentId = R.drawable.frame_white_2x;
                break;
            case 1:
                mPreviewFrameView.setImageResource(R.drawable.frame_black_2x);
                currentId = R.drawable.frame_black_2x;
                break;
            case 2:
                mPreviewFrameView.setImageResource(R.drawable.frame_pistachio);
                currentId = R.drawable.frame_pistachio;
                break;
            case 3:
                mPreviewFrameView.setImageResource(R.drawable.frame_lemon);
                currentId = R.drawable.frame_lemon;
                break;
            default:
                return;
        }
    }

//    @Override
//    // Doodle Fragment
//    public void onDoodleFragmentInteraction(int pos) {
//
//    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_to_doodle, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_go_to_doodle) {
//            Bitmap frameBmp = ((BitmapDrawable)mPreviewFrameView.getDrawable()).getBitmap();
            Bitmap frameBmp = BitmapFactory.decodeResource(getResources(), currentId);
            Bitmap photoBmp = testBitmap;
            mergedImages = createSingleImageFromMultipleImages(frameBmp, photoBmp);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            mergedImages.compress(Bitmap.CompressFormat.JPEG, 50, stream);
            byte[] byteArray = stream.toByteArray();
            Intent intent = new Intent(this, DoodleActivity.class);
            intent.putExtra(EXTRA_MERGED_IMAGE, byteArray);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
