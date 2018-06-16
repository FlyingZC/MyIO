package com.zc.file;

import java.io.File;

/**
 * @author zc
 */
public class T02File
{
    public static void main(String[] args)
    {
        File file = new File("hello.txt");
        File f = file.getAbsoluteFile();
        System.out.println(f);
        String filePath = file.getAbsolutePath();
        //C:\Users\Administrator\Workspaces\MyEclipse 10\MyIO\hello.txt
        System.out.println(filePath);
    }
}
