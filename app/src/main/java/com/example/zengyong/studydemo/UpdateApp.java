package com.example.zengyong.studydemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class UpdateApp {

	public static void main(String[] args) {
		ServerSocket server;
		try {
			// 创建一个ServerSocket对象，并让这个Socket在 10005 端口监听
			server = new ServerSocket(10005);
				// 调用ServerSocket的accept()方法，接受客户端所发送的请求，
				// 如果客户端没有发送数据，那么该线程就停滞不继续
			while(true){
				Socket socket = server.accept();
				// 从Socket当中得到InputStream对象
				InputStream inputStream = socket.getInputStream();
				OutputStream outputStream = socket.getOutputStream();
				byte[] reDate = new byte[1024 * 4];
				int temp = 0;
				// 从InputStream当中读取客户端所发送的数据\
				String str = "";
				while ((temp = inputStream.read(reDate)) != -1) {
					 str = new String(reDate, 0, temp) ;
					
					System.out.println(str);
				}
				if("你好".equals(str)){
					FileInputStream fis = new FileInputStream("d://app-debug.apk");
					// 获取Socket的OutputStream对象用于发送数据。
//					outputStream.write("你也好啊".getBytes());
		            byte[] sendDate = new byte[1024 ];
					int tem = 0;
					while ((tem = fis.read(sendDate)) != -1) {
						outputStream.write(sendDate, 0, tem);
						System.out.println(tem);
					}
					outputStream.flush();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}



}
