package com.example.zengyong.studydemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ReadFile {
	public static void main(String[] args) {
		try{
		FileInputStream fis = new FileInputStream("d://app_test_1.apk");
		FileOutputStream fos = new FileOutputStream(new File("d://app_copy.apk"));
		byte[] sendDate = new byte[1024 * 4];
		int tem = 0;
		while ((tem = fis.read(sendDate)) != -1) {
			fos.write(sendDate, 0, tem);
			System.out.println(tem);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
