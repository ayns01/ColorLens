package com.example.ayana.chekikkov1.Paint;

import android.graphics.Path;

public class FingerPath {
    public int color;
    public int strokeWidth;
    public Path mPath;

    public FingerPath(int color, int strokeWidth, Path path) {
        this.color = color;
        this.strokeWidth = strokeWidth;
        mPath = path;
    }
}
