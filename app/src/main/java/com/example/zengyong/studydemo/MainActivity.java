package com.example.zengyong.studydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.zengyong.studydemo.adb.RunAdbActivity;
import com.example.zengyong.studydemo.chart.ChartActivity;
import com.example.zengyong.studydemo.common.SwitchActivity;
import com.example.zengyong.studydemo.device.DevcieInfoActivity;
import com.example.zengyong.studydemo.dialog.DialogFragmentActivity;
import com.example.zengyong.studydemo.mapbox.MapBoxActivity;
import com.example.zengyong.studydemo.openlaeyers.OpenLayersActivity;
import com.example.zengyong.studydemo.setting.SettingsActivity;
import com.example.zengyong.studydemo.view.CoustomViewActivity;
import com.example.zengyong.studydemo.voice.VoiceActivity;
import com.zy.mylibrary.LoginActivity;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        try {
            Object service = getSystemService("statusbar");
            Class<?> claz = Class.forName("android.app.StatusBarManager");
            Method expand = claz.getMethod("collapsePanels");
            expand.invoke(service);
        } catch (Exception e) {
            e.printStackTrace();
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        MainAdapter adapter = new MainAdapter();
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ClassBean bean = (ClassBean) adapter.getData().get(position);
                startActivity(new Intent(MainActivity.this, bean.aClass));
            }
        });

        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(Settings.ACTION_SETTINGS));
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        adapter.setNewData(initDatas());

        Log.d("MainActivity", "onCreate: " + "获取到MAC地址为：" + MacUtils.getMobileMAC(this));


        //当前可用运行内存
        String availMemory = SystemMemoryUtils.getAvailMemory(this);
        //当前总运行内存
        String totalMemory = SystemMemoryUtils.getTotalMemory(this);

        Log.d("MainActivity", "【可用内存】" + availMemory + "【总内存】" + totalMemory);

        if (StorageUtils.isExternalStorageAvailable()) {
            Log.d("MainActivity", "【外部存储可用】"
                    + StorageUtils.getAvailableExternalMemorySize(this) +
                    "【外部总空间】" + StorageUtils.getExternalMemorySize(this));
        }
        Log.d("MainActivity", "【内部存储可用】"
                + StorageUtils.getAvailableInternalMemorySize(this) +
                "【内部总空间】" + StorageUtils.getInternalMemorySize(this));
    }

    private List<ClassBean> initDatas() {
        List<ClassBean> list = new ArrayList<>();
        list.add(new ClassBean("switch控件的使用", SwitchActivity.class));
        list.add(new ClassBean("进度条自定义", CoustomViewActivity.class));
        list.add(new ClassBean("mapbox地图", MapBoxActivity.class));
        list.add(new ClassBean("openlayers地图", OpenLayersActivity.class));
        list.add(new ClassBean("跳系统设置界面", SettingsActivity.class));
        list.add(new ClassBean("设备信息", DevcieInfoActivity.class));
        list.add(new ClassBean("执行adb命令", RunAdbActivity.class));
        list.add(new ClassBean("语音播报", VoiceActivity.class));
        list.add(new ClassBean("对话框", DialogFragmentActivity.class));
        list.add(new ClassBean("柱状图", ChartActivity.class));
        return list;
    }


    class MainAdapter extends BaseQuickAdapter<ClassBean, BaseViewHolder> {

        public MainAdapter() {
            super(R.layout.adapter_main_layout);
        }

        @Override
        protected void convert(BaseViewHolder helper, ClassBean item) {
            helper.setText(R.id.title, item.desc);
        }
    }

}
