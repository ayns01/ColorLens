package com.example.ayana.chekikkov1;

import android.graphics.Bitmap;

public class SavedPhoto {
    private String savedDate;
//    private int savedPhoto;
    private Bitmap savedBitmap;

    public SavedPhoto() {

    }

//    public SavedPhoto(String savedDate, int savedPhoto) {
//        this.savedDate = savedDate;
//        this.savedPhoto = savedPhoto;
//    }

    public SavedPhoto(String savedDate, Bitmap savedBitmap) {
        this.savedDate = savedDate;
        this.savedBitmap = savedBitmap;
    }

    public String getSavedDate() {
        return savedDate;
    }

    public void setSavedDate(String savedDate) {
        this.savedDate = savedDate;
    }

    public Bitmap getSavedPhoto() {
        return savedBitmap ;
    }

    public void setSavedPhoto(Bitmap savedBmp) {
        this.savedBitmap = savedBmp;
    }
}
