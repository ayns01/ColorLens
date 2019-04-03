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
    private static int DEFAULT_COLOR;
    private boolean startDoodle = false;
    private static final float TOUCH_TOLERANCE = 4;

    private ArrayList<Paint> paintPenList =new ArrayList<>();
    private Path latestPath;
    private Paint latestPaint;
    private ArrayList<Path> pathPenList =new ArrayList<>();
    private int lineWidth =15;
    private int currentColor;
    private float mX, mY;

    public static int ALPHA = 200;
    public int RED = 0x99;
    public int GREEN = 0xCC;
    public int BLUE = 0xFF;


    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        DEFAULT_COLOR= Color.BLACK;
        currentColor=DEFAULT_COLOR;

        initPaintNPen(currentColor);

    }

    public void chooseColor(int r, int g, int b) {
        this.RED = r;
        this.GREEN = g;
        this.BLUE = b;

        startDoodle = true;
    }

    private void initPaintNPen(int color){

        latestPaint=getNewPaintPen(color);
        latestPaint.setShader(new LinearGradient(0,0,100,100, Color.argb(ALPHA,RED,GREEN,BLUE),
                Color.argb(ALPHA,RED,GREEN,BLUE), Shader.TileMode.REPEAT));
        latestPath=getNewPathPen();

        paintPenList.add(latestPaint);
        pathPenList.add(latestPath);

    }

    private Path getNewPathPen() {
        Path path=new Path();
        return path;
    }

    private Paint getNewPaintPen(int color){

        Paint mPaintPen =new Paint();

        mPaintPen.setStrokeWidth(lineWidth);
        mPaintPen.setAntiAlias(true);
        mPaintPen.setDither(true);
        mPaintPen.setStyle(Paint.Style.STROKE);
        mPaintPen.setStrokeJoin(Paint.Join.ROUND);
        mPaintPen.setStrokeCap(Paint.Cap.ROUND);
        mPaintPen.setColor(color);

        return mPaintPen;

    }

    @Override
    public boolean onTouchEvent( MotionEvent event) {
        float x=event.getX();
        float y=event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            startPath(x, y);
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            updatePath(x, y);
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            endPath(x, y);
        }
        invalidate();
        return true;
    }

    private void startPath(float x, float y) {

        initPaintNPen(currentColor);
        latestPath.moveTo(x, y);
        mX = x;
        mY = y;
        latestPath.lineTo(x, y);
    }

    private void updatePath(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);

        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            latestPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    private void endPath(float x, float y) {

    }

    public void setDrawColor(int color) {

        currentColor=color;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for(int i=0;i<paintPenList.size();i++){
            canvas.drawPath(pathPenList.get(i),paintPenList.get(i));
        }
    }

    public void increaseWidth(boolean decrease){

        if(decrease){
            if(lineWidth >5) {
                lineWidth = lineWidth - 10;
            }
        }else{
            if(lineWidth <50) {
                lineWidth = lineWidth + 10;
            }
        }

        invalidate();
    }

    public void resetView() {
        currentColor=DEFAULT_COLOR;

        latestPath.reset();
        latestPaint.reset();

        pathPenList.clear();
        paintPenList.clear();
        lineWidth = 20;

        initPaintNPen(currentColor);

        invalidate();
    }

    public Bitmap getBitmap()
    {
        this.setDrawingCacheEnabled(true);
        this.buildDrawingCache();
        Bitmap bmp = Bitmap.createBitmap(this.getDrawingCache());
        this.setDrawingCacheEnabled(false);

        return bmp;
    }


    public void undoPath() {

        if(paintPenList.size()>1) {
            latestPaint = paintPenList.get(paintPenList.size() - 2);
            latestPath = pathPenList.get(pathPenList.size() - 2);

            paintPenList.remove(paintPenList.size() - 1);
            pathPenList.remove(pathPenList.size() - 1);

            currentColor=latestPaint.getColor();
            lineWidth= (int) latestPaint.getStrokeWidth();
        }else{
            resetView();
        }

        invalidate();
    }
}
