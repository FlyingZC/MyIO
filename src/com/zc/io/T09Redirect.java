package com.zc.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import org.junit.Test;

public class T09Redirect
{
    /**
     * I/O重定向操作的是字节流,System类提供了一些简单的静态方法调用,以允许对标准输入,输出和错误I/O流进行重定向
     * setIn(InputStream)
     * setOut(PrintStream)
     * setErr(PrintStream)
     * @throws Exception
     */
    @Test
    public void test01() throws Exception
    {
        PrintStream console = System.out;
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("src/main/java/io/Redirecting.java"));
        PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream("test.txt")));
        //设置标准输入流的来源
        System.setIn(in);
        //设置标准输出流的来源
        
        System.setOut(out);
        System.setErr(out);
        //读取标准输入流
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = br.readLine()) != null)
            System.out.println(s);//此时 标准输出流的位置已经被重定向到文件中
        out.close(); // Remember this!
        System.setOut(console);//重新恢复标准输出流到控制台
    }
}
