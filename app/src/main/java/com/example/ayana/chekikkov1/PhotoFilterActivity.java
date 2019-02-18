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
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.ayana.chekikkov1.Adapter.TabPageAdapter;
import com.example.ayana.chekikkov1.FilterImage.FilterToImage;

import java.io.ByteArrayOutputStream;

public class PhotoFilterActivity extends AppCompatActivity implements
                                                    ColorsFragment.OnFragmentInteractionListener,
                                                    FramesFragment.OnFragmentInteractionListener {
    Bitmap bmp;
    private Bitmap frameImage;
    ImageView mPreviewImageView;
    ImageView mPreviewFrameView;
    Bitmap testBitmap;

    private int currentId = R.drawable.frame_white;

    public static final String EXTRA_PHOTO_IMAGE = "com.example.ayana.chekikkov1.extra.PHOTO.IMAGE";
    public static final String EXTRA_FRAME_IMAGE = "com.example.ayana.chekikkov1.extra.FRAME.IMAGE";

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

        testBitmap = bmp;

        frameImage = BitmapFactory.decodeResource(getResources(), R.drawable.frame_white);

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

    @Override
    // ColorsFragment
    public void onFragmentInteraction(int pos) {
        switch (pos) {
            case 0:
                testBitmap = Bitmap.createBitmap(bmp.getWidth(),
                        bmp.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(testBitmap);
                Paint paint = new Paint();
                ColorMatrix originalMatrix = new FilterToImage().backToOriginal();
                paint.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(originalMatrix)));
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
                mPreviewImageView.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(redMatrix)));
                canvas2.drawBitmap(bmp, 0, 0, paint2);
                break;
            case 2:
                testBitmap = Bitmap.createBitmap(bmp.getWidth(),
                        bmp.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas3 = new Canvas(testBitmap);
                Paint paint3 = new Paint();
                ColorMatrix orangeMatrix = new FilterToImage().applyOrangeFilter();
                paint3.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(orangeMatrix)));
                mPreviewImageView.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(orangeMatrix)));
                canvas3.drawBitmap(bmp, 0, 0, paint3);
                break;
            case 3:
                testBitmap = Bitmap.createBitmap(bmp.getWidth(),
                        bmp.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas4 = new Canvas(testBitmap);
                Paint paint4 = new Paint();
                ColorMatrix blueMatrix = new FilterToImage().applyBlueFilter();
                paint4.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(blueMatrix)));
                mPreviewImageView.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(blueMatrix)));
                canvas4.drawBitmap(bmp, 0, 0, paint4);
                break;
            case 4:
                testBitmap = Bitmap.createBitmap(bmp.getWidth(),
                        bmp.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas5 = new Canvas(testBitmap);
                Paint paint5 = new Paint();
                ColorMatrix greenMatrix = new FilterToImage().applyGreenFilter();
                paint5.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(greenMatrix)));
                mPreviewImageView.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(greenMatrix)));
                canvas5.drawBitmap(bmp, 0, 0, paint5);
                break;
            case 5:
                testBitmap = Bitmap.createBitmap(bmp.getWidth(),
                        bmp.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas6 = new Canvas(testBitmap);
                Paint paint6 = new Paint();
                ColorMatrix purpleMatrix = new FilterToImage().applyPurpleFilter();
                paint6.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(purpleMatrix)));
                mPreviewImageView.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(purpleMatrix)));
                canvas6.drawBitmap(bmp, 0, 0, paint6);
//                testBitmap = Bitmap.createBitmap(bmp.getWidth(),
//                        bmp.getHeight(), Bitmap.Config.ARGB_8888);
//                Canvas canvas6 = new Canvas(testBitmap);
//                Paint paint6 = new Paint();
//                ColorFilter duoChinaColor = new FilterToImage().duotoneColorFilter(Color.BLACK, Color.WHITE, 1.3f);
//                paint6.setColorFilter(duoChinaColor);
//                mPreviewImageView.setColorFilter(duoChinaColor);
//                canvas6.drawBitmap(bmp, 0, 0, paint6);
                break;
            case 6:
                testBitmap = Bitmap.createBitmap(bmp.getWidth(),
                        bmp.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas7 = new Canvas(testBitmap);
                Paint paint7 = new Paint();
                ColorMatrix whiteMatrix = new FilterToImage().applyWhiteFilter();
                paint7.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(whiteMatrix)));
                mPreviewImageView.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(whiteMatrix)));
                canvas7.drawBitmap(bmp, 0, 0, paint7);
                break;
            case 7:
                testBitmap = Bitmap.createBitmap(bmp.getWidth(),
                        bmp.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas8 = new Canvas(testBitmap);
                Paint paint8 = new Paint();
                ColorMatrix yellowMatrix = new FilterToImage().applyYellowFilter();
                paint8.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(yellowMatrix)));
                mPreviewImageView.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(yellowMatrix)));
                canvas8.drawBitmap(bmp, 0, 0, paint8);
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
                mPreviewFrameView.setImageResource(R.drawable.frame_white);
                currentId = R.drawable.frame_white;
                break;
            case 1:
                mPreviewFrameView.setImageResource(R.drawable.frame_black);
                currentId = R.drawable.frame_black;
                break;
            case 2:
                mPreviewFrameView.setImageResource(R.drawable.frame_lemon);
                currentId = R.drawable.frame_lemon;
                break;
            case 3:
                mPreviewFrameView.setImageResource(R.drawable.frame_pink);
                currentId = R.drawable.frame_pink;
                break;
            case 4:
                mPreviewFrameView.setImageResource(R.drawable.frame_papermint);
                currentId = R.drawable.frame_papermint;
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
            Bitmap photoBmp = testBitmap;
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photoBmp.compress(Bitmap.CompressFormat.JPEG, 90, stream);
            byte[] byteArray = stream.toByteArray();
            Intent intent = new Intent(this, DoodleActivity.class);
            intent.putExtra(EXTRA_PHOTO_IMAGE, byteArray);
            intent.putExtra(EXTRA_FRAME_IMAGE, currentId);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
