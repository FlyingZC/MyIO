package com.zc.exer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;

public class T01FileInputStream {
	public static void main(String[] args) throws Exception {
		File file=new File("hello.txt");
		FileInputStream fis=new FileInputStream(file);
		//read()返回下一个数据字节(但是返回int型)；如果到达流的末尾，则返回 -1。
		int b;
		while((b=fis.read())!=-1){
			System.out.println((char)b);
		}
		fis.close();
	}
	
	@Test
	public void test01() throws Exception{
		File file=new File("e:\\hello.txt");
		FileInputStream fis=new FileInputStream(file);
		byte[] b=new byte[10];
		int len;
		//每次读取b字节数组大小的数据缓存在b里.返回读取的长度
		while((len=fis.read(b))!=-1){
			System.out.println(new String(b,0,len));
		}
		try {
			if(fis!=null){
				fis.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
