package com.example.zengyong.studydemo.chart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;

import com.example.zengyong.studydemo.R;

public class ChartActivity extends AppCompatActivity {

    private HorizontalScrollView horiView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        horiView = (HorizontalScrollView) findViewById(R.id.horizontalScrollView1);
        horiView.setPadding(70, 0, 0, 0);

        //设置horizontalScrollvView拉到头和尾的时候没有阴影颜色
        horiView.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }
}
