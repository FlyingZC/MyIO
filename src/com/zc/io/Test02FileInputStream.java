package com.zc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class Test02FileInputStream {
	//从硬盘存在的文件中.读取内容
	@Test
	public void test1() throws IOException{
		File file=new File("hello.txt");
		FileInputStream fis = new FileInputStream(file);
		//read()一个字节一个字节的读byte
		/*int b=fis.read();
		//没读到结尾
		while(b!=-1){
			//因为返回的b是int型.转为char.中文会乱码.一个中文两个字节.read()默认每次读一个byte
			System.out.println((char)b);
			b=fis.read();
		}*/
		int b;
		//简写
		while(((b=fis.read())!=-1)){
			System.out.println((char)b);
		}
		//需要显式关闭流.
		fis.close();
	}
	
	@Test
	public void test2(){
		File file=new File("hello.txt");
		FileInputStream fis=null; 
		try {
			fis=new FileInputStream(file);
			//存放每次读取数据的 数组
			byte[] b=new byte[20];
			//每次读取到b中的字节的长度
			int len;
			while((len=fis.read(b))!=-1){
//				for(int i=0;i<len;i++){
//					System.out.println((char)b[i]);
//				}
				String str=new String(b,0,len);
				System.out.println(str);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(fis!=null){
				fis.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
