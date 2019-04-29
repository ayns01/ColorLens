package com.example.ayana.chekikkov1;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.example.ayana.chekikkov1.Adapter.SavedPhotoAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int RESULT_PICK_IMAGEFILE = 1001;
    public static final String EXTRA_URI = "com.example.ayana.chekikkov1.extra.URI";

    ImageView mInitView;

    private RecyclerView cardRecyclerView;
    private SavedPhotoAdapter adapter;
    private List<SavedPhoto> mSavedPhotoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        int[] initImageList = {R.drawable.fish, R.drawable.rain, R.drawable.dog};
//        mInitView = findViewById(R.id.init_image);
//        Random random = new Random();
//        int i = random.nextInt(initImageList.length);
//        mInitView.setImageResource(initImageList[i]);
//        initCollapsingToolbar();


        cardRecyclerView = findViewById(R.id.card_recycler_view);
        mSavedPhotoList = new ArrayList<>();
        adapter = new SavedPhotoAdapter(this, mSavedPhotoList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        cardRecyclerView.setLayoutManager(mLayoutManager);
        cardRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        cardRecyclerView.setItemAnimator(new DefaultItemAnimator());
        cardRecyclerView.setAdapter(adapter);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            byte[] savedPhotoByteArray = extras.getByteArray(PhotoFilterActivity.EXTRA_SAVED_PHOTO);
            Bitmap savedPhotoBmp = BitmapFactory.decodeByteArray(savedPhotoByteArray, 0, savedPhotoByteArray.length);
            String savedDate = extras.getString(PhotoFilterActivity.EXTRA_SAVED_DATE);
            Log.d("TAG", "onCreate: " + savedDate);

            SavedPhoto savedPhoto = new SavedPhoto(savedDate, savedPhotoBmp);
            mSavedPhotoList.add(savedPhoto);
        }
        
        prepareDefaultPhotos();

//        try {
//            Glide.with(this).load(R.drawable.fish).into((ImageView) findViewById(R.id.backdrop));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, RESULT_PICK_IMAGEFILE);
            }
        });
    }

    private void prepareDefaultPhotos() {
        Bitmap[] covers = new Bitmap[]{
                BitmapFactory.decodeResource(this.getResources(),
                        R.drawable.fish),
                BitmapFactory.decodeResource(this.getResources(),
                        R.drawable.rain),
                BitmapFactory.decodeResource(this.getResources(),
                        R.drawable.dog),
                BitmapFactory.decodeResource(this.getResources(),
                        R.drawable.cooking),
        };

        SavedPhoto a = new SavedPhoto("Sample", covers[0]);
        mSavedPhotoList.add(a);

        SavedPhoto b = new SavedPhoto("Sample", covers[1]);
        mSavedPhotoList.add(b);

        SavedPhoto c = new SavedPhoto("Sample", covers[2]);
        mSavedPhotoList.add(c);

        SavedPhoto d = new SavedPhoto("Sample", covers[3]);
        mSavedPhotoList.add(d);

        adapter.notifyDataSetChanged();
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        if (requestCode == RESULT_PICK_IMAGEFILE && resultCode == Activity.RESULT_OK) {
            if (resultData.getData() != null) {

                Uri selectedImage = resultData.getData();

                Intent intent = new Intent(this, CropActivity.class);
                intent.putExtra(EXTRA_URI, selectedImage.toString());
                startActivity(intent);
            }
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
