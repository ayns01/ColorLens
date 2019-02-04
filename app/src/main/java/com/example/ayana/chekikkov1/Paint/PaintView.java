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
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class PaintView extends View {
    public static int BLUSH_SIZE = 30;
    public static final int DEFAULT_COLOR = Color.BLACK;
    private static final float TOUCH_TOLERANCE = 4;
    private float mX, mY;
    private Path mPath;
    private Paint mPaint;
    private ArrayList<FingerPath> paths = new ArrayList<>();
    private int currentColor;
    private int strokeWidth;

    private Canvas mCanvas;
    private Bitmap mBitmap;
    private Paint mBitmapPoint = new Paint(Paint.DITHER_FLAG);

    private int alpha = 220;
    private int red = 0x99;
    private int green = 0xCC;
    private int blue = 0xFF;

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
        mPaint.setShader(new LinearGradient(0,0,100,100,Color.argb(alpha,red,green,blue),
                Color.argb(alpha,red,green,blue), Shader.TileMode.REPEAT));
    }

    public void init(Bitmap bmp) {
//        int height = h;
//        int width = w;

        mBitmap = bmp.copy(Bitmap.Config.ARGB_8888, true);
//        mBitmap = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

        currentColor = DEFAULT_COLOR;
        strokeWidth = BLUSH_SIZE;
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
                break;
        }

        return true;
    }
}
