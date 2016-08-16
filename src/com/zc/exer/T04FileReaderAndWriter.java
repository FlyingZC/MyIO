package com.zc.exer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

public class T04FileReaderAndWriter {
	public static void main(String[] args) {
		File file=new File("e:\\Day01.txt");
		FileReader fr=null;
		try {
			fr=new FileReader(file);
			char[] c=new char[24];
			int len;
			while((len=fr.read(c))!=-1){
				System.out.println(new String(c,0,len));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test01(){
		File file1=new File("e:\\Day01.txt");
		File file2=new File("e:\\Day01copy.txt");
		FileReader fr=null;
		FileWriter fw=null;
		try {
			fr=new FileReader(file1);
			fw=new FileWriter(file2);
			char[] c=new char[24];
			int len;
			while((len=fr.read(c))!=-1){
				fw.write(c,0,len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(fr!=null){
				try {
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fw!=null){
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}
}
