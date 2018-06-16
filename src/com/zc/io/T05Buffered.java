package com.zc.io;

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

import org.junit.Test;

/**
 * @author flyingzc
 * 使用缓冲流可以提高文件操作的效率.通常都使用缓冲流
 */
public class T05Buffered
{
    public void testBufferedInputOutput() throws Exception
    {
        //将节点流对象作为参数创建缓冲流
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try
        {
            File file1 = new File("hello.txt");
            File file2 = new File("hello2.txt");
            //
            FileInputStream fis = new FileInputStream(file1);
            FileOutputStream fos = new FileOutputStream(file2);
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);
            //
            byte[] b = new byte[1024];
            int len;
            while ((len = bis.read(b)) != -1)
            {
                bos.write(b, 0, len);
                //缓冲流输出最后flush.因为最后一次可能不满
                bos.flush();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            //关闭缓冲流
            if (bos != null)
            {
                bos.close();
            }
            if (bis != null)
            {
                bis.close();
            }
        }

    }

    @Test
    public void testBufferedReaderWriter() throws Exception
    {
        File file = new File("read.txt");
        File file2 = new File("readcopy.txt");
        FileReader reader = new FileReader(file);
        FileWriter writer = new FileWriter(file2);
        BufferedReader bufferedReader = new BufferedReader(reader);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        /*char[] c=new char[20];
        int len;
        while((len=(bufferedReader.read(c)))!=-1){
        	bufferedWriter.write(c, 0, len);
        	bufferedWriter.flush();
        }*/

        //使用bufferedReader.readLine().每次读一行.将读出的字符串返回.读到最后返回null
        String str;
        while ((str = (bufferedReader.readLine())) != null)
        {
            bufferedWriter.write(str);
            //输出换行
            bufferedWriter.newLine();
            System.out.println(str);
            bufferedWriter.flush();
        }
        bufferedReader.close();
        bufferedWriter.close();
    }
    
    /**
     * BufferedReader和BufferedWriter简写
     * @throws Exception 
     */
    @Test
    public void simpleBufferedReaderWriter() throws Exception
    {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("read.txt"));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("readcopy.txt"));
        String oneLine;
        while((oneLine = (bufferedReader.readLine())) != null)
        {
            bufferedWriter.write(oneLine);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
        bufferedReader.close();
        bufferedWriter.close();
    }

}
