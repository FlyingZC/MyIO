package com.zc.exer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class T03CopyFile {
	public static void main(String[] args){
		//注意 若文件隐藏了拓展名.注意拓展名
		File file1=new File("e:\\myfile.txt");
		File file2=new File("e:\\copyfile.txt");
		FileInputStream fis=null;
		FileOutputStream fos=null;
		try {
			fis=new FileInputStream(file1);
			fos=new FileOutputStream(file2);
			byte[] b=new byte[10];
			int len;
			while((len=fis.read(b))!=-1){
				System.out.println(new String(b,0,len));
				fos.write(b,0,len);
			}
			
		} catch (Exception e) {
			//记得打印异常信息
			e.printStackTrace();
		}finally{
			try {
				if(fis!=null){
					fis.close();
				}
				if(fos!=null){
					fos.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}	
		}
	}
		
	
}
