package com.jakeesveld.sleeptracker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class SleepGraph extends android.support.v7.widget.AppCompatImageView {
    Paint paint1, paint2;
    float yEnd4, yEnd5, yEnd6, yEnd7, yEnd8, yEnd9, yEnd10, yEnd11, yEnd12;
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
        paint1 = new Paint();
        paint2 = new Paint();
        paint1.setColor(getResources().getColor(R.color.colorAccent));
        paint2.setColor(getResources().getColor(R.color.colorPrimary));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float yStart = getHeight();

        canvas.drawRect(100, yEnd4, 200, yStart, paint1);
        canvas.drawRect(200, yEnd5, 300, yStart, paint2);
        canvas.drawRect(300, yEnd6, 400, yStart, paint1);
        canvas.drawRect(400, yEnd7, 500, yStart, paint2);
        canvas.drawRect(500, yEnd8, 600, yStart, paint1);
        canvas.drawRect(600, yEnd9, 700, yStart, paint2);
        canvas.drawRect(700, yEnd10, 800, yStart, paint1);
        canvas.drawRect(800, yEnd11, 900, yStart, paint2);
        canvas.drawRect(900, yEnd12, 1000, yStart, paint1);
        super.onDraw(canvas);
    }
}
