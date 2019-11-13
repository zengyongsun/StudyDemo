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
import android.widget.ProgressBar;

import com.example.zengyong.studydemo.R;

/**
 * @author : Zeyo
 * e-mail : zengyongsun@163.com
 * date   : 2019/9/27 10:36
 * desc   :
 * version: 1.0
 */
public class CustomerCarProgressBar extends ProgressBar {

    private Context mContext;

    public CustomerCarProgressBar(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public CustomerCarProgressBar(Context context, AttributeSet attrs) {
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
