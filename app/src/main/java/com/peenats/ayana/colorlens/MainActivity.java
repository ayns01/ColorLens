package com.peenats.ayana.colorlens;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int RESULT_PICK_IMAGEFILE = 1001;
    public static final String EXTRA_URI = "com.example.ayana.chekikkov1.extra.URI";

    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 2;

    ImageView mInitView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int[] initImageList = {R.drawable.emo1, R.drawable.emo2, R.drawable.emo3, R.drawable.emo4,
                R.drawable.emo5, R.drawable.emo6, R.drawable.emo7};
        mInitView = findViewById(R.id.init_image);
        Random random = new Random();
        int i = random.nextInt(initImageList.length);
        mInitView.setImageResource(initImageList[i]);

        // Floating button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                readStorage();
            }
        });
    }

    public void readStorage() {
        int permissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, RESULT_PICK_IMAGEFILE);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE
            );
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted.
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, RESULT_PICK_IMAGEFILE);
                } else {
                    // User refused to grant permission.
                }
            }
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }

    public void openBrowserForPrivacyPolicy(MenuItem item) {

        if (Locale.getDefault().getLanguage().equals("ja")) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ayns01.github.io/ColorLensApp/PrivacyPolicy/Japanese"));
            startActivity(browserIntent);
        }else {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ayns01.github.io/ColorLensApp/PrivacyPolicy/English"));
            startActivity(browserIntent);
        }
    }
}
