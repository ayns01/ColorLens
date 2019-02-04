package com.example.ayana.chekikkov1.Paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class PaintView extends View {
    public static int BLUSH_SIZE = 25;
    public static final int DEFAULT_COLOR = Color.BLACK;
    private static final float TOUCH_TOLERANCE = 4;

    public static int ALPHA = 55;
    public static int RED = 0x99;
    public static int GREEN = 0xCC;
    public static int BLUE = 0xFF;

    private float mX, mY;
    // info of current path
    private Path mPath;
    private Paint mPaint;
    // history
    private ArrayList<FingerPath> paths = new ArrayList<>();

    private int currentColor;
    private int strokeWidth;

    private Canvas mCanvas;
    private Bitmap mBitmap;
    private Paint mBitmapPoint = new Paint(Paint.DITHER_FLAG);

    public PaintView(Context context) { super(context); }

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(DEFAULT_COLOR);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setXfermode(null);
    }

    public void init(Bitmap bmp, int r, int g, int b) {
        this.RED = r;
        this.GREEN = g;
        this.BLUE = b;
        mBitmap = bmp;
        mCanvas = new Canvas(mBitmap);

        currentColor = DEFAULT_COLOR;
        strokeWidth = BLUSH_SIZE;

        mPaint.setShader(new LinearGradient(0,0,100,100,Color.argb(ALPHA,RED,GREEN,BLUE),
                Color.argb(ALPHA,RED,GREEN,BLUE), Shader.TileMode.REPEAT));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();

        for (FingerPath fp: paths) {
            mPaint.setColor(fp.color);
            mPaint.setStrokeWidth(fp.strokeWidth);
            mPaint.setMaskFilter(null);
            mCanvas.drawPath(fp.mPath, mPaint);
        }

        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPoint);
        canvas.restore();
    }

    private void touchStart(float x, float y) {

        mPath = new Path();
        FingerPath fp = new FingerPath(currentColor, strokeWidth, mPath);
        paths.add(fp);

        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touchMove(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);

        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    private void touchUp() {
        mPath.lineTo(mX, mY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchStart(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touchUp();
                invalidate();
                this.mPath.reset();
                break;
        }

        return true;
    }
}
