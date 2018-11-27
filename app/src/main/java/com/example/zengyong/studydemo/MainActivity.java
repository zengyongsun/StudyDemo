package com.example.zengyong.studydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

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
        new Thread(new Runnable() {
            @Override
            public void run() {
                connectServerWithTCPSocket();
            }
        }).start();

    }
}
