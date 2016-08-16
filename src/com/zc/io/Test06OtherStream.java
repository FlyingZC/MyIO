package com.zc.io;
//1.转换流 InputStreamReader OutputStreamWriter
//2.标准的输入输出流
//标准的输出流:System.out
//标准的输入流:System.in :返回InputStream类型
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.junit.Test;

//转换流
public class Test06OtherStream {
	//转换流
	@Test
	public void test1() throws Exception{
		File file=new File("read.txt");
		FileInputStream fis=new FileInputStream(file);
		//解码 字节->字符
		//转换流(InputStream in,解码方式) 
		//InputStreamReader 讲InputStream转换为Reader
		InputStreamReader inputStreamReader=new InputStreamReader(fis,"utf-8");
		//可以再包裹缓冲流
		BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
		//编码
		File file1=new File("readcopy2.txt");
		FileOutputStream fileOutputStream=new FileOutputStream(file1);
		//转换流.将字节流 转换为 字符流 输出 (out,编码方式)
		OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream,"utf-8");
		BufferedWriter bufferedWriter=new BufferedWriter(outputStreamWriter);
		String str;
		while((str=bufferedReader.readLine())!=null){
			bufferedWriter.write(str);
			bufferedWriter.newLine();
			bufferedWriter.flush();
		}
	}
	
	@Test
	public void test2() throws Exception{
		//输入小写字符 输出大写
		//键盘System.in
		InputStream inputStream=System.in;
		//转换流 将字节流转换为字符流
		InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
		BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
		System.out.println("请输入字符串:");
		String str;
		//一直循环
		while(true){
			//每次读取一行
			str=bufferedReader.readLine();
			if(str.equalsIgnoreCase("e")||str.equalsIgnoreCase("exit")){
				break;
			}
			String str1=str.toUpperCase();
			//打印
			System.out.println(str1);
		}
		bufferedReader.close();
	}
}
