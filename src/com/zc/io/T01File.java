package com.zc.io;

import java.io.File;

public class T01File
{
    public void test1()
    {
        File file1 = new File("d:/io/helloworld.txt");
        System.out.println(file1.getName());
        System.out.println(file1.getPath());
        System.out.println(file1.getAbsoluteFile());
        System.out.println(file1.getParent());
        System.out.println(file1.getAbsolutePath());
    }
}
