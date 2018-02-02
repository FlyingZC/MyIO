package com.zc.z01pipe;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class T01Pipe {
	public static void main(String[] args) throws IOException {
		final PipedOutputStream output=new PipedOutputStream();
		final PipedInputStream input=new PipedInputStream(output);
		Thread t1=new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					output.write("Hello world,pipe!".getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		Thread t2=new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					int data;
					while((data=input.read())!=-1){
						System.out.println((char)data);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		t1.start();
		t2.start();
	}
}
