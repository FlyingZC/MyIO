package com.zc.exer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

public class T05Buffered {
	/**
	 * BufferedInputStream和BufferedOutputStream
	 * @param args
	 */
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
					e.printStackTrace();
				}
			}
			if(bos!=null){
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * BufferedReader
	 * @throws IOException 
	 */
	@Test
	public void test01() throws IOException{
		//File file=new File("e:\\Day01.txt");
		BufferedReader br=null;
		try {
			br=new BufferedReader(new FileReader("e:\\Day01.txt"));
			String s;
			while((s=br.readLine())!=null){
				System.out.println(s);
			}
		} catch (Exception e) {
			
		}finally{
		    br.close();
		}
	}
	
	/**
	 * BufferedReader和BufferedWriter
	 */
	@Test
	public void test02(){
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
	
	/**
	 * BufferedReader和BufferedWriter复制文件
	 */
	@Test
	public void testBufferedReaderAndWriter()
	{
	    BufferedReader br = null;
	    BufferedWriter bw = null;
	    try
        {
            br = new BufferedReader(new FileReader("hello.txt"));
            bw = new BufferedWriter(new FileWriter("helloBWCopy.txt"));
            String str;
            while((str = br.readLine())!=null)
            {
                bw.write(str);
                bw.newLine();
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
	    finally
	    {
	        try
            {
                br.close();
                bw.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
	    }
	}
}
