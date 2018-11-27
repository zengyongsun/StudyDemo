package com.example.zengyong.studydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.downMsg)
    TextView downMsg;
    Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



    }

    protected void connectServerWithTCPSocket() {


        try {// 创建一个Socket对象，并指定服务端的IP及端口号
            socket = new Socket("192.168.0.110", 10005);
            // 获取Socket的OutputStream对象用于发送数据。
            OutputStream outputStream = socket.getOutputStream();

            outputStream.write("你好".getBytes("utf-8"));
            // 发送读取的数据到服务端
            outputStream.flush();
            //不加上这句后面的，获取输入流没有信息，阻塞
            socket.shutdownOutput();
            InputStream stream = socket.getInputStream();
            byte[] by = new byte[1024];
            int tem = 0;
            FileOutputStream fos = null;
            File file = new File(getCacheDir() + "test.apk");
            if(!file.exists()){
                file.mkdirs();
            }
            fos = new FileOutputStream(file);
            while ((tem = stream.read(by)) != -1) {
                fos.write(by, 0, tem);
                Log.d("InputStream", tem + "");
            }
            if (fos != null) {
                fos.flush();
            }
            Log.d("InputStream", "=====");


//            InputStream stream = socket.getInputStream();
//            byte[] reDate = new byte[1024 * 4];
//            int temp = 0;
//            // 从InputStream当中读取客户端所发送的数据\
//            String str = "";
//            while ((temp = stream.read(reDate)) != -1) {
//                str = new String(reDate, 0, temp);
//
//                Log.d("InputStream", "=====" + str);
//            }

            /** 或创建一个报文，使用BufferedWriter写入,看你的需求 **/
//          String socketData = "[2143213;21343fjks;213]";
//          BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
//                  socket.getOutputStream()));
//          writer.write(socketData.replace("\n", " ") + "\n");
//          writer.flush();
            /************************************************/
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @OnClick(R.id.downMsg)
    public void onViewClicked() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                connectServerWithTCPSocket();
//            }
//        }).start();
        Log.d("InputStream", "=====" + install(getCacheDir() + "test.apk"));
    }

    public boolean install(String apkPath) {
        boolean result = false;
        DataOutputStream dataOutputStream = null;
        BufferedReader errorStream = null;
        try {
            // 申请su权限
            Process process = Runtime.getRuntime().exec("su");
            dataOutputStream = new DataOutputStream(process.getOutputStream());
            // 执行pm install命令
            String command = "pm install -r " + apkPath + "\n";
            dataOutputStream.write(command.getBytes(Charset.forName("utf-8")));
            dataOutputStream.flush();
            dataOutputStream.writeBytes("exit\n");
            dataOutputStream.flush();
            process.waitFor();
            errorStream = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String msg = "";
            String line;
            // 读取命令的执行结果
            while ((line = errorStream.readLine()) != null) {
                msg += line;
            }
            Log.d("TAG", "install msg is " + msg);
            // 如果执行结果中包含Failure字样就认为是安装失败，否则就认为安装成功
            if (!msg.contains("Failure")) {
                result = true;
            }
        } catch (Exception e) {
            Log.e("TAG", e.getMessage(), e);
        } finally {
            try {
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                if (errorStream != null) {
                    errorStream.close();
                }
            } catch (IOException e) {
                Log.e("TAG", e.getMessage(), e);
            }
        }
        return result;
    }
}
