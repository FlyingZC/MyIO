package com.zc.io;

import java.io.File;

import org.junit.Test;

public class T01File
{
    @Test
    public void testFileApi()
    {
        File file1 = new File("hello.txt");
        System.out.println(file1.getName());
        System.out.println(file1.getPath());
        System.out.println(file1.getAbsoluteFile());
        System.out.println(file1.getParent());
        System.out.println(file1.getAbsolutePath());
    }

    @Test
    public void testProperty()
    {
        String temp = System.getProperty("java.io.tmpdir");
        System.out.println(isDir(temp));
    }
    
    /**
     * 传入的字符串 是否 为目录
     * @param temp
     * @return
     */
    public static boolean isDir(String temp)
    {
        if (temp == null || (!(new File(temp)).exists()) || (!(new File(temp)).isDirectory()))
            return false;
        return true;
    }
}
