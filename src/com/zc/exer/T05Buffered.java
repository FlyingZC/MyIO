package com.zc.exer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

public class T05Buffered {
	public static void main(String[] args) {
		File file=new File("e:\\Day01.txt");
		File file2=new File("e:\\Day01copy.txt");
		
		FileInputStream fis=null;
		BufferedInputStream bis=null;
		
		FileOutputStream fos=null;
		BufferedOutputStream bos=null;
		try {
			fis=new FileInputStream(file);
			bis=new BufferedInputStream(fis);
			
			fos=new FileOutputStream(file2);
			bos=new BufferedOutputStream(fos);
			
			byte[] b=new byte[1024];
			int len;
			while((len=bis.read(b))!=-1){
				bos.write(b,0, len);
				bos.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(bis!=null){
				try {
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(bos!=null){
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	@Test
	public void test01(){
		File file=new File("e:\\Day01.txt");
		FileReader fr=null;
		BufferedReader br=null;
		try {
			fr=new FileReader(file);
			br=new BufferedReader(fr);
			String s;
			while((s=br.readLine())!=null){
				System.out.println(s);
			}
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void tset02(){
		File file=new File("e:\\Day01.txt");
		File file1=new File("e:\\Day01Buff.txt");
		FileReader fr=null;
		BufferedReader br=null;
		
		FileWriter fw=null;
		BufferedWriter bw=null;
		
		try {
			fr=new FileReader(file);
			br=new BufferedReader(fr);
			
			fw=new FileWriter(file1);
			bw=new BufferedWriter(fw);
			String s;
			while((s=br.readLine())!=null){
				bw.write(s);
				bw.newLine();
				System.out.println(s);
				//加flush()
				bw.flush();
			}
			
		} catch (Exception e) {
			
		}
	}
}
