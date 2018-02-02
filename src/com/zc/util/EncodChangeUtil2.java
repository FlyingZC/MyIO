package com.zc.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * 完成字符转码
 * @author zc
 *
 */
public class EncodChangeUtil2 {
	//C:\\Users\\Administrator\\Workspaces\\MyEclipse 10\\MyChat\\src
	private static String srcName="F:\\yuan\\SuperMario8.1_FINAL";
	private static String descName;
	private static File file=new File(srcName);
	private static File desc;
	
	private static String projectName;
	private static int proStart;
	private static int proEnd;
	
	public static void main(String[] args) {
		proStart=srcName.lastIndexOf("\\")+1;
		proEnd=srcName.length();
		projectName=srcName.substring(proStart,proEnd);
		System.out.println("projcetName:"+projectName);
		trace(file);
	}
	
	public EncodChangeUtil2(){
													//最后一条\的位置
		projectName=srcName.substring(srcName.lastIndexOf("\\")+1,srcName.length());
	}
	
	/**
	 * 遍历所有文件夹
	 */
	public static void trace(File file){
		if(file.isDirectory()){
			//复制后的项目根目录名
			descName=new StringBuilder(srcName).append("Copy").toString();
			desc=new File(descName);
			desc.mkdir();
			
			File[] fs=file.listFiles();
			for(File f:fs){
				if(f.isDirectory()){
					String srcDirName=f.getAbsolutePath();
					String descDirName=new StringBuilder(srcDirName).replace(proStart, proEnd, projectName+"Copy").toString();
					desc=new File(descDirName);
					if(!desc.exists()){
						desc.mkdirs();
					}
					trace(f);
				}
				if(f.isFile()){
					String srcDirName=f.getAbsolutePath();
					String descDirName=new StringBuilder(srcDirName).replace(proStart, proEnd, projectName+"Copy").toString();
					desc=new File(descDirName);
					//以.java结尾的,调用转码方法
					if(f.getName().endsWith(".java")){
						codeChange(f,desc);
					}else{
						//其他文件直接复制
						copyFile(f,desc);
					}
				}
			}
		}
	}
	
	/**
	 * 复制普通文件(无需转码的文件)
	 * @param src
	 * @param desc
	 */
	private static void copyFile(File src,File desc) {
		FileInputStream fis=null;
		FileOutputStream fos=null;
		try {
			fis=new FileInputStream(src);
			fos=new FileOutputStream(desc);
			byte[] b=new byte[1024];
			int len=-1;
			while((len=fis.read(b))!=-1){
				fos.write(b,0,len);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
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
	 * 转码
	 * @param file
	 */
	public static void codeChange(File src,File desc){
		BufferedReader br=null;
		PrintWriter pw =null;
		try {
			FileInputStream fis = new FileInputStream(src);
			InputStreamReader isr = new InputStreamReader(fis,"GBK");
			br = new BufferedReader(isr);
			FileOutputStream fos=new FileOutputStream(desc);
			OutputStreamWriter osw = new OutputStreamWriter(fos,"utf-8");
			pw = new PrintWriter(osw,true);
			String str=null;
			while((str=br.readLine())!=null){
					pw.println(str);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(br!=null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(pw!=null){
				pw.close();
			}
		}
	}
}
