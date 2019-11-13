package com.example.zengyong.studydemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.zengyong.studydemo.R;

/**
 * @author : Zeyo
 * e-mail : zengyongsun@163.com
 * date   : 2019/9/27 10:36
 * desc   :
 * version: 1.0
 */
public class CustomerProgressBar extends ProgressBar {

    private Context mContext;

    public CustomerProgressBar(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public CustomerProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    private Paint mPaint;

    private void init() {
        setIndeterminate(false);
        setIndeterminate(false);
        setIndeterminateDrawable(ContextCompat.getDrawable(mContext,
                android.R.drawable.progress_indeterminate_horizontal));
        setProgressDrawable(ContextCompat.getDrawable(mContext,
                R.drawable.progress_bar_bg));
        setMax(100);
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setTypeface(Typeface.MONOSPACE);

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        //进度  30 4d  60 99
        mPaint.setColor(Color.parseColor("#4DFFFFFF"));
        mPaint.setStrokeWidth(DensityUtils.dp2px(mContext, 5));
        float x = getProgress() * (getWidth() / 100);
        Log.d("CustomerProgressBar", "draw: " + getProgress());
        for (int i = 0; i < x; i += 15) {
            canvas.drawLine(i, 0, i + 15, getHeight() + 10, mPaint);
        }

        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_progress_bar_car);
        float iconX = getProgress() * (getWidth() / 100) - icon.getWidth() / 2;
        float iconY = getHeight() / 2 - 15;
        mPaint.setColor(Color.WHITE);
        canvas.drawBitmap(icon, iconX, iconY, mPaint);


        if (!icon.isRecycled()) {
            icon.isRecycled();
        }

    }

}
