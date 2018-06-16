package com.zc.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.junit.Test;

//打印流 默认输出到控制台.也可以更改输出位置
//System.out返回一个打印流对象
//System.out.println()打印流里的println()方法
//可以将
public class T07PrintStream
{

    @Test
    public void test1()
    {
        FileOutputStream fileOutputStream = null;
        try
        {
            fileOutputStream = new FileOutputStream(new File("testPrint.txt"));
            //打印输出流.true自动刷新
            PrintStream printStream = new PrintStream(fileOutputStream, true);
            if (printStream != null)
            {
                //把标准输出流改成输出到文件
                System.setOut(printStream);
            }
            System.out.println("I love you");
            printStream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }
}
