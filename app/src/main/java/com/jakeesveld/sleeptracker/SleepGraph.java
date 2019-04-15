package com.jakeesveld.sleeptracker;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class SleepGraph extends View {
    public SleepGraph(Context context) {
        super(context);
    }

    public SleepGraph(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SleepGraph(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SleepGraph(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void init(AttributeSet attrs){

    }

    @Override
    protected void onDraw(Canvas canvas) {


        super.onDraw(canvas);
    }
}
