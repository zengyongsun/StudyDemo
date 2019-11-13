package com.example.zengyong.studydemo.adb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.zy.viewlib.dialog.ShowMessageDialog;

import com.example.zengyong.studydemo.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RunAdbActivity extends AppCompatActivity {

    @BindView(R.id.etAdb)
    EditText etAdb;
    @BindView(R.id.tvShow)
    TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_adb);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btExecute)
    public void onViewExecuteClicked() {
        String command = etAdb.getText().toString();
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuffer output = new StringBuffer();
            int read;
            char[] buff = new char[2048];
            while ((read = bufferedReader.read(buff)) != -1) {
                output.append(buff, 0, read);
            }
            tvShow.setText(output.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("RunAdbActivity", "onViewExecuteClicked: " + e.getMessage());
        }

    }

    @OnClick(R.id.btPrompt)
    public void onViewPromptClicked() {
        ShowMessageDialog dialog = new ShowMessageDialog(this);
        dialog.setTitle("adb命令提示");
        dialog.setShowContent("网络查询：netcfg \n" +
                "ping IP地址：ping -c3 目标ip地址 \n" +
                "查看Android设备属性的命令：getprop \n" +
                "修改IP：ifconfig eth0 ip地址 netmask 255.255.255.0\n" +
                "关闭\\开启网卡：busybox ifconfig eth0 down\\up" +
                "注意：修改完IP后，需要重启网卡");
        dialog.setMessageDialogListener(new ShowMessageDialog.ShowMessageDialogListener() {
            @Override
            public void yes() {

            }

            @Override
            public void no() {

            }
        });
        dialog.show();
    }
}
