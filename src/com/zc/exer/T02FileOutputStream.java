package com.zc.exer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class T02FileOutputStream {
	public static void main(String[] args) {
		File file=new File("e:\\hello.txt");
		try {
			FileOutputStream fos=new FileOutputStream(file);
			//写入时会覆盖掉源文件里的内容
			byte[] b=new String("I Low U vzzzzzzzzzzzzzzzzzzz").getBytes();
			fos.write(b);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
