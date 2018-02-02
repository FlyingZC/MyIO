package com.zc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

public class T03FileOutputStream
{
    //写一个文件到硬盘
    @Test
    public void test1()
    {
        //物理文件可以不存在.自动创建
        File file = new File("hello2.txt");
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(file);
            //new String("I love China").getBytes():将字符串转换为字节数组
            fos.write(new String("I love China").getBytes());
        }
        catch (Exception e)
        {
        }
        finally
        {
            if (fos != null)
            {
                try
                {
                    fos.close();
                }
                catch (Exception e)
                {
                }
            }
        }
    }

    //文件复制.字节流
    @Test
    public void test2()
    {
        //读取一个文件.写入到另一个位置.相当于复制
        //1.提供写入.写出的文件
        //一定带后缀
        File file1 = new File("hello.txt");
        File file3 = new File("hello3.txt");
        //2.提供相应的流
        FileInputStream fis = null;
        FileOutputStream fos = null;
        //3.实现文件的复制
        try
        {
            fis = new FileInputStream(file1);
            fos = new FileOutputStream(file3);

            byte[] b = new byte[20];
            int len;
            while ((len = fis.read(b)) != -1)
            {
                fos.write(b, 0, len);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (fos != null)
            {
                try
                {
                    fis.close();
                    fos.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public void test3()
    {
        long before = System.currentTimeMillis();
        String src = "C:\\Users\\Administrator\\Desktop\\j\\by0102.cpp";
        String src2 = "C:\\Users\\Administrator\\Desktop\\copy.txt";
        fileCopy(src, src2);
        long after = System.currentTimeMillis();
    }

    public static void fileCopy(String src, String src2)
    {
        //读取一个文件.写入到另一个位置.相当于复制
        //1.提供写入.写出的文件
        //一定带后缀
        File file1 = new File(src);
        File file3 = new File(src2);
        //2.提供相应的流
        FileInputStream fis = null;
        FileOutputStream fos = null;
        //3.实现文件的复制
        try
        {
            fis = new FileInputStream(file1);
            fos = new FileOutputStream(file3);

            byte[] b = new byte[20];
            int len;
            while ((len = fis.read(b)) != -1)
            {
                fos.write(b, 0, len);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (fos != null)
            {
                try
                {
                    fos.close();
                }
                catch (Exception e2)
                {
                }
                finally
                {
                    if (fis != null)
                    {
                        try
                        {
                            fis.close();
                        }
                        catch (Exception e2)
                        {
                        }
                    }
                }
            }
        }
    }

}
