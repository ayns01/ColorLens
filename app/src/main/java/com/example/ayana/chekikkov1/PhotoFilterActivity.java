package com.example.ayana.chekikkov1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.net.Uri;
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
import com.example.ayana.chekikkov1.Paint.PaintView;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhotoFilterActivity extends AppCompatActivity implements
                                                    ColorsFragment.OnFragmentInteractionListener,
                                                    FramesFragment.OnFragmentInteractionListener,
                                                    DoodleFragment.OnFragmentInteractionListener,
                                                    DoodleFragment.OnFragmentUndoListener,
                                                    DoodleFragment.OnFragmentDefaultPenListener,
                                                    DoodleFragment.OnFragmentPoscaPenListener{
    private Bitmap mSentBitmap;
    private Bitmap mFrameBitmap;
    private Bitmap mPhotoBitmap;
    private Bitmap mDoodleBitmap;
    private ImageView mPreviewImageView;
    private ImageView mPreviewFrameView;
    private PaintView mPaintView;
    private Bitmap mResultBitmap;

    private int frameDrawableId = R.drawable.frame_white;

    private static final int REQUEST_SAVE_IMAGE = 1002;
    public static final String EXTRA_SAVED_DATE = "com.example.ayana.chekikkov1.extra.SAVED.DATE";
    public static final String EXTRA_SAVED_PHOTO = "com.example.ayana.chekikkov1.extra.SAVED.PHOTO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_filter);

        final ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("");

        Bundle extras = getIntent().getExtras();
        byte[] byteArray = extras.getByteArray(CropActivity.EXTRA_CROPPED_IMAGE);
        mSentBitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        mPreviewImageView = findViewById(R.id.previewImageView);
        mPreviewFrameView = findViewById(R.id.previewFrameView);
        mPaintView = findViewById(R.id.paintView);

        mPhotoBitmap = Bitmap.createBitmap(mSentBitmap.getWidth(),
                mSentBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mPhotoBitmap);

        mFrameBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.frame_white);

        mDoodleBitmap = Bitmap.createBitmap(mFrameBitmap.getWidth() - 35,
                mFrameBitmap.getHeight() - 35,
                Bitmap.Config.ARGB_8888);

        // image for previewing
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(1.1f);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(cm);
        mPreviewImageView.setColorFilter(filter);
        mPreviewImageView.setImageBitmap(mPhotoBitmap);
        mPreviewFrameView.setImageBitmap(mFrameBitmap);

        // image for saving
        ColorMatrix cm2 = new ColorMatrix();
        cm2.setSaturation(1.75f);
        ColorMatrixColorFilter filter2 = new ColorMatrixColorFilter(cm2);
        Paint paint = new Paint();
        paint.setColorFilter(filter2);
        canvas.drawBitmap(mSentBitmap, 0, 0, paint);


        final TabLayout tabLayout = findViewById(R.id.tablayout);
        final ViewPager viewPager = findViewById(R.id.viewPager);

        TabPageAdapter tabPageAdapter = new TabPageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(tabPageAdapter);
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
    // FilterFragment
    public void onFragmentInteraction(int pos) {
        switch (pos) {
            case 0:
                mPhotoBitmap = Bitmap.createBitmap(mSentBitmap.getWidth(),
                        mSentBitmap.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(mPhotoBitmap);
                Paint paint = new Paint();
                ColorMatrix cm = new ColorMatrix();
                cm.setSaturation(1.75f);
                ColorMatrixColorFilter filter = new ColorMatrixColorFilter(cm);
                paint.setColorFilter(filter);
                mPreviewImageView.setColorFilter(filter);
                canvas.drawBitmap(mSentBitmap, 0, 0, paint);
                break;
            case 1:
                mPhotoBitmap = Bitmap.createBitmap(mSentBitmap.getWidth(),
                        mSentBitmap.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas2 = new Canvas(mPhotoBitmap);
                Paint paint2 = new Paint();
                ColorMatrix redMatrix = new FilterToImage().applyRedFilter();
                paint2.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(redMatrix)));
                mPreviewImageView.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(redMatrix)));
                canvas2.drawBitmap(mSentBitmap, 0, 0, paint2);
                break;
            case 2:
                mPhotoBitmap = Bitmap.createBitmap(mSentBitmap.getWidth(),
                        mSentBitmap.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas3 = new Canvas(mPhotoBitmap);
                Paint paint3 = new Paint();
                ColorMatrix orangeMatrix = new FilterToImage().applyOrangeFilter();
                paint3.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(orangeMatrix)));
                mPreviewImageView.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(orangeMatrix)));
                canvas3.drawBitmap(mSentBitmap, 0, 0, paint3);
                break;
            case 3:
                mPhotoBitmap = Bitmap.createBitmap(mSentBitmap.getWidth(),
                        mSentBitmap.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas4 = new Canvas(mPhotoBitmap);
                Paint paint4 = new Paint();
                ColorMatrix blueMatrix = new FilterToImage().applyBlueFilter();
                paint4.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(blueMatrix)));
                mPreviewImageView.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(blueMatrix)));
                canvas4.drawBitmap(mSentBitmap, 0, 0, paint4);
                break;
            case 4:
                mPhotoBitmap = Bitmap.createBitmap(mSentBitmap.getWidth(),
                        mSentBitmap.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas5 = new Canvas(mPhotoBitmap);
                Paint paint5 = new Paint();
                ColorMatrix greenMatrix = new FilterToImage().applyGreenFilter();
                paint5.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(greenMatrix)));
                mPreviewImageView.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(greenMatrix)));
                canvas5.drawBitmap(mSentBitmap, 0, 0, paint5);
                break;
            case 5:
                mPhotoBitmap = Bitmap.createBitmap(mSentBitmap.getWidth(),
                        mSentBitmap.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas6 = new Canvas(mPhotoBitmap);
                Paint paint6 = new Paint();
                ColorMatrix purpleMatrix = new FilterToImage().applyPurpleFilter();
                paint6.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(purpleMatrix)));
                mPreviewImageView.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(purpleMatrix)));
                canvas6.drawBitmap(mSentBitmap, 0, 0, paint6);
                break;
            case 6:
                mPhotoBitmap = Bitmap.createBitmap(mSentBitmap.getWidth(),
                        mSentBitmap.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas7 = new Canvas(mPhotoBitmap);
                Paint paint7 = new Paint();
                ColorMatrix whiteMatrix = new FilterToImage().applyWhiteFilter();
                paint7.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(whiteMatrix)));
                mPreviewImageView.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(whiteMatrix)));
                canvas7.drawBitmap(mSentBitmap, 0, 0, paint7);
                break;
            case 7:
                mPhotoBitmap = Bitmap.createBitmap(mSentBitmap.getWidth(),
                        mSentBitmap.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas8 = new Canvas(mPhotoBitmap);
                Paint paint8 = new Paint();
                ColorMatrix yellowMatrix = new FilterToImage().applyYellowFilter();
                paint8.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(yellowMatrix)));
                mPreviewImageView.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(yellowMatrix)));
                canvas8.drawBitmap(mSentBitmap, 0, 0, paint8);
                break;
            default:
                return;
        }
    }

    @Override
    // FrameFragment
    public void onFramesFragmentInteraction(int pos) {
        switch (pos) {
            case 0:
                mPreviewFrameView.setImageResource(R.drawable.frame_white);
                frameDrawableId = R.drawable.frame_white;
                break;
            case 1:
                mPreviewFrameView.setImageResource(R.drawable.frame_black);
                frameDrawableId = R.drawable.frame_black;
                break;
            case 2:
                mPreviewFrameView.setImageResource(R.drawable.frame_yellow);
                frameDrawableId = R.drawable.frame_yellow;
                break;
            case 3:
                mPreviewFrameView.setImageResource(R.drawable.frame_pink);
                frameDrawableId = R.drawable.frame_pink;
                break;
            case 4:
                mPreviewFrameView.setImageResource(R.drawable.frame_paleblue);
                frameDrawableId = R.drawable.frame_paleblue;
                break;
            case 5:
                mPreviewFrameView.setImageResource(R.drawable.frame_orange);
                frameDrawableId = R.drawable.frame_orange;
                break;
            case 6:
                mPreviewFrameView.setImageResource(R.drawable.frame_drop);
                frameDrawableId = R.drawable.frame_drop;
                break;
            case 7:
                mPreviewFrameView.setImageResource(R.drawable.frame_star);
                frameDrawableId = R.drawable.frame_star;
                break;
            default:
                return;
        }
    }

    @Override
    // Doodle Fragment
    public void onDoodleFragmentInteraction(int pos) {
        switch (pos) {
            case 0:
                // black
                mPaintView.chooseColor(0x16, 0x16, 0x16);
                break;
            case 1:
                // gold (metallic)
                mPaintView.chooseColor(212,175,55);
                break;
            case 2:
                // pastel_blue
                mPaintView.chooseColor(160, 195, 210);
                break;
            case 3:
                // lavender_gray
                mPaintView.chooseColor(190, 190, 209);
                break;
            case 4:
                // queen_pink
                mPaintView.chooseColor(248, 205, 210);
                break;
            case 5:
                // orange_yellow
                mPaintView.chooseColor(249, 200, 99);
                break;
            case 6:
                // white
                mPaintView.chooseColor(255, 255, 255);
                break;
            case 7:
                // deep_moss_green
                mPaintView.chooseColor(53,94,59);
                break;
            case 8:
                // deep_peach
                mPaintView.chooseColor(255,203,164);
                break;
            case 9:
                // deep_pink
                mPaintView.chooseColor(255,20,147);
                break;
            case 10:
                // maastricht_blue
                mPaintView.chooseColor(0,28,61);
                break;
            case 11:
                // deep_puce
                mPaintView.chooseColor(169,92,104);
                break;
            case 12:
                // deep_carmine_pink
                mPaintView.chooseColor(239,48,56);
                break;
            case 13:
                // deep_lilac
                mPaintView.chooseColor(153,85,187);
                break;
            case 14:
                // aero_blue
                mPaintView.chooseColor(201,255,225);
                break;
            case 15:
                // sea_serpent
                mPaintView.chooseColor(75,199,207);
                break;
            default:
                return;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_save) {
            Bitmap backWallBmp = BitmapFactory.decodeResource(this.getResources(),
                    R.drawable.backwall);
            mResultBitmap = Bitmap.createBitmap(backWallBmp.getWidth(), backWallBmp.getHeight(),
                    backWallBmp.getConfig());
            Canvas canvas = new Canvas(mResultBitmap);
            Bitmap frameResBmp = BitmapFactory.decodeResource(getResources(), frameDrawableId);
            Bitmap photoSize = BitmapFactory.decodeResource(this.getResources(),
                            R.drawable.photo_size_criteria);
            Bitmap photoResBmp = Bitmap.createScaledBitmap(mPhotoBitmap,
                    photoSize.getWidth(),
                    photoSize.getHeight(),
                    false);
            Bitmap doodleBmp = mPaintView.getBitmap();
            Bitmap paintResBmp = Bitmap.createScaledBitmap(doodleBmp, mDoodleBitmap.getWidth(),
                    mDoodleBitmap.getHeight(), false);
            int leftOfFrame = (backWallBmp.getWidth() - mFrameBitmap.getWidth()) / 2;
            int topOfFrame = (backWallBmp.getHeight() - mFrameBitmap.getHeight()) / 2;
            int topOfPhoto = (int)(((backWallBmp.getHeight() - photoResBmp.getHeight()) / 2) - (topOfFrame * 1.8));
            int leftOfPhoto = (backWallBmp.getWidth() - photoResBmp.getWidth()) / 2;
            canvas.drawBitmap(backWallBmp, 0, 0, null);
            canvas.drawBitmap(photoResBmp, leftOfPhoto, topOfPhoto, null);
            canvas.drawBitmap(frameResBmp, leftOfFrame, topOfFrame, null);
            canvas.drawBitmap(paintResBmp, leftOfFrame + 15, topOfFrame + 5, null);

            Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
            intent.setType("image/jpeg");
            intent.putExtra(Intent.EXTRA_TITLE, System.currentTimeMillis() + "-picha.jpeg");
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
                    mResultBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }



        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String currentDateandTime = sdf.format(new Date());

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        mResultBitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream);
        byte[] savedPhotoByteArray = stream.toByteArray();

        Intent i = new Intent(this, MainActivity.class);
        i.putExtra(EXTRA_SAVED_DATE, currentDateandTime);
        i.putExtra(EXTRA_SAVED_PHOTO, savedPhotoByteArray);

        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    @Override
    public void onDoodleFragmentUndoInteraction() {
        mPaintView.undoPath();
    }

    @Override
    public void onDoodleFragmentDefaultPenInteraction() { mPaintView.setPen(1); }

    @Override
    public void onDoodleFragmentPoscaPenInteraction() {
        mPaintView.setPen(2);
    }
}
