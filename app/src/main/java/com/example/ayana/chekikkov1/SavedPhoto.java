package com.example.ayana.chekikkov1;

public class SavedPhoto {
    private String savedDate;
    private int savedPhoto;

    public SavedPhoto() {

    }

    public SavedPhoto(String savedDate, int savedPhoto) {
        this.savedDate = savedDate;
        this.savedPhoto = savedPhoto;
    }

    public String getSavedDate() {
        return savedDate;
    }

    public void setSavedDate(String savedDate) {
        this.savedDate = savedDate;
    }

    public int getSavedPhoto() {
        return savedPhoto;
    }

    public void setSavedPhoto(int savedPhoto) {
        this.savedPhoto = savedPhoto;
    }
}
