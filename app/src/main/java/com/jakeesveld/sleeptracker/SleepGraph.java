package com.jakeesveld.sleeptracker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class SleepGraph extends android.support.v7.widget.AppCompatImageView {
    Paint paint1, paint2, paint3;
    float yEnd4, yEnd5, yEnd6, yEnd7, yEnd8, yEnd9, yEnd10, yEnd11, yEnd12, yOffset;
    public SleepGraph(Context context) {
        super(context);
        init(null);
    }

    public SleepGraph(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SleepGraph(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }
    public void setYEndings(ArrayList<Integer> endingList){
        yEnd4 = endingList.get(0);
        yEnd5 = endingList.get(1);
        yEnd6 = endingList.get(2);
        yEnd7 = endingList.get(3);
        yEnd8 = endingList.get(4);
        yEnd9 = endingList.get(5);
        yEnd10 = endingList.get(6);
        yEnd11 = endingList.get(7);
        yEnd12 = endingList.get(8);
        yOffset = getHighest();
        invalidate();
    }


    public void init(AttributeSet attrs){
        if(attrs != null){
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SleepGraph);
            yEnd4 = typedArray.getInt(R.styleable.SleepGraph_yEnd4, 0);
            yEnd5 = typedArray.getInt(R.styleable.SleepGraph_yEnd5, 0);
            yEnd6 = typedArray.getInt(R.styleable.SleepGraph_yEnd6, 0);
            yEnd7 = typedArray.getInt(R.styleable.SleepGraph_yEnd7, 0);
            yEnd8 = typedArray.getInt(R.styleable.SleepGraph_yEnd8, 0);
            yEnd9 = typedArray.getInt(R.styleable.SleepGraph_yEnd9, 0);
            yEnd10 = typedArray.getInt(R.styleable.SleepGraph_yEnd10, 0);
            yEnd11 = typedArray.getInt(R.styleable.SleepGraph_yEnd11, 0);
            yEnd12 = typedArray.getInt(R.styleable.SleepGraph_yEnd12, 0);
        }
        yOffset = getHighest();
        paint1 = new Paint();
        paint2 = new Paint();
        paint3 = new Paint();
        paint1.setColor(getResources().getColor(R.color.colorAccent));
        paint2.setColor(getResources().getColor(R.color.colorPrimary));
        paint3.setColor(getResources().getColor(android.R.color.white));
        paint3.setTextSize(32);


    }

    private float getHighest(){
        float highestNumber = 0;
        if(yEnd4 > highestNumber){
            highestNumber = yEnd4;
        }
        if(yEnd5 > highestNumber){
            highestNumber = yEnd5;
        }
        if(yEnd6 > highestNumber){
            highestNumber = yEnd6;
        }
        if(yEnd7 > highestNumber){
            highestNumber = yEnd7;
        }
        if(yEnd8 > highestNumber){
            highestNumber = yEnd8;
        }
        if(yEnd9 > highestNumber){
            highestNumber = yEnd9;
        }
        if(yEnd10 > highestNumber){
            highestNumber = yEnd10;
        }
        if(yEnd11 > highestNumber){
            highestNumber = yEnd11;
        }
        if(yEnd12 > highestNumber){
            highestNumber = yEnd12;
        }
        return highestNumber;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float yEnd = getHeight();
        float yStart = getHeight() / yOffset;
        float xBaseLine = getWidth() / 10f;

        canvas.drawRect(xBaseLine * 1, yEnd - (yStart * yEnd4), xBaseLine * 2, yEnd, paint1);
        canvas.drawRect(xBaseLine * 2, yEnd - (yStart * yEnd5), xBaseLine * 3, yEnd, paint2);
        canvas.drawRect(xBaseLine * 3, yEnd - (yStart * yEnd6), xBaseLine * 4, yEnd, paint1);
        canvas.drawRect(xBaseLine * 4, yEnd - (yStart * yEnd7), xBaseLine * 5, yEnd, paint2);
        canvas.drawRect(xBaseLine * 5, yEnd - (yStart * yEnd8), xBaseLine * 6, yEnd, paint1);
        canvas.drawRect(xBaseLine * 6, yEnd - (yStart * yEnd9), xBaseLine * 7, yEnd, paint2);
        canvas.drawRect(xBaseLine * 7, yEnd - (yStart * yEnd10), xBaseLine * 8, yEnd, paint1);
        canvas.drawRect(xBaseLine * 8, yEnd - (yStart * yEnd11), xBaseLine * 9, yEnd, paint2);
        canvas.drawRect(xBaseLine * 9, yEnd - (yStart * yEnd12), xBaseLine * 10, yEnd, paint1);
        canvas.drawText("4 hrs", xBaseLine * 1.25f, yEnd, paint3);
        canvas.drawText("5 hrs", xBaseLine * 2.25f, yEnd, paint3);
        canvas.drawText("6 hrs", xBaseLine * 3.25f, yEnd, paint3);
        canvas.drawText("7 hrs", xBaseLine * 4.25f, yEnd, paint3);
        canvas.drawText("8 hrs", xBaseLine * 5.25f, yEnd, paint3);
        canvas.drawText("9 hrs", xBaseLine * 6.25f, yEnd, paint3);
        canvas.drawText("10 hrs", xBaseLine * 7.25f, yEnd, paint3);
        canvas.drawText("11 hrs", xBaseLine * 8.25f, yEnd, paint3);
        canvas.drawText("12 hrs", xBaseLine * 9.25f, yEnd, paint3);
        super.onDraw(canvas);
    }
}
