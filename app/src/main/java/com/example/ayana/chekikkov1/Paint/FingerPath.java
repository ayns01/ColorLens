package com.example.ayana.chekikkov1.Paint;

import android.graphics.Paint;
import android.graphics.Path;

public class FingerPath {
    public int color;
    public int strokeWidth;
    public Path mPath;
    public Paint mPaint;

    public FingerPath(int color, int strokeWidth, Path path, Paint paint) {
        this.color = color;
        this.strokeWidth = strokeWidth;
        mPath = path;
        mPaint = paint;
    }
}
