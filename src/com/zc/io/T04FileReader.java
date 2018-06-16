package com.zc.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

/**
 * @author flyingzc
 * 字符输入流
 */
public class T04FileReader
{

    @Test
    public void testFileReader() throws IOException
    {
        File file = new File("read.txt");
        FileReader fr = new FileReader(file);
        char[] c = new char[24];
        int len;
        while ((len = fr.read(c)) != -1)
        {
            String str = new String(c, 0, len);
            System.out.println(str);
        }
        fr.close();
    }

    //文件复制.字符流.效率高
    //对于非文本文件.只能使用字节流.比如视频.音频
    @Test
    public void testFileCopy() throws IOException
    {
        File file1 = new File("read.txt");
        File file2 = new File("read_copy.txt");
        FileReader fr = new FileReader(file1);
        FileWriter fw = new FileWriter(file2);
        char[] c = new char[24];
        int len;
        while ((len = fr.read(c)) != -1)
        {
            String str = new String(c, 0, len);
            fw.write(str);
        }
        fr.close();
        fw.close();
    }

}
