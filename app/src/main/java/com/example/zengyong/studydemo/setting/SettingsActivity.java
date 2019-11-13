package com.example.zengyong.studydemo.setting;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.zengyong.studydemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends AppCompatActivity {

    @BindView(R.id.oneLocation)
    Button oneLocation;
    @BindView(R.id.twoWlan)
    Button twoWlan;
    @BindView(R.id.threeShow)
    Button threeShow;
    @BindView(R.id.voice)
    Button voice;
    @BindView(R.id.wifi)
    Button wifi;
    @BindView(R.id.time)
    Button time;
    @BindView(R.id.setIp)
    Button setIp;
    @BindView(R.id.input)
    Button input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
    }

    private void goIntent(String action) {
        Intent it = new Intent(action);
        it.putExtra("extra_prefs_show_button_bar", true);
        it.putExtra("extra_prefs_set_next_text", "");
        it.putExtra("extra_prefs_set_back_text", "返回");
        startActivity(it);
    }

    @OnClick({R.id.oneLocation, R.id.twoWlan, R.id.threeShow, R.id.voice, R.id.wifi, R.id.time,
            R.id.setIp, R.id.input, R.id.selectLanguage,R.id.accessibility})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.oneLocation:
                goIntent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                break;
            case R.id.twoWlan:
                goIntent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
                break;
            case R.id.threeShow:
                goIntent(Settings.ACTION_DISPLAY_SETTINGS);
                break;
            case R.id.voice:
                //音量、铃声
                goIntent(Settings.ACTION_SOUND_SETTINGS);
                break;
            case R.id.wifi:
                goIntent(Settings.ACTION_WIFI_IP_SETTINGS);
                break;
            case R.id.time:
                //时间日期
                goIntent(Settings.ACTION_DATE_SETTINGS);
                break;
            case R.id.accessibility:
                //辅助界面
                goIntent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                break;
            case R.id.input:
                goIntent(Settings.ACTION_INPUT_METHOD_SETTINGS);
            case R.id.selectLanguage:
                goIntent(Settings.ACTION_LOCALE_SETTINGS);
                break;
            case R.id.setIp:

                break;
            default:
                break;
        }
    }


}
