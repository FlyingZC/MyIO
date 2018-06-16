package com.zc.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class EncodeUtil
{
    private String repStr;

    @Test
    public void testChangeEncode()
    {
        changeEncode(new File("E:\\a.txt"), "utf-8", "gbk", new File("E:\\acopy.txt"));
    }

    @Test
    public void testFile()
    {
        File f = new File("E:/f1/a.txt");
        System.out.println(f.getAbsolutePath());//E:\f1\a.txt
        System.out.println(f.getAbsoluteFile());
        System.out.println(f.getName());//a.txt
        System.out.println(f.getParentFile());//E:\f1
        System.out.println(f.getParent());
    }

    @Test
    public void changeDir()
    {
        String baseDir = "f1";
        String folder = "E:/f1/a.txt";
        File f = new File("");
        //编辑正则表达式    
        Pattern p = Pattern.compile(baseDir);
        //匹配器.接收 要匹配的串
        Matcher m = p.matcher(folder);
        //输入替换的串
        String result = m.replaceFirst("fCopy");
        System.out.println("result:" + result);

        //拆分
        String[] strs = p.split(baseDir);
        //输出正则表达式
        System.out.println(p.pattern());
        //是否匹配
        p.matcher(baseDir);

    }

    public static void main(String[] args)
    {
        EncodeUtil util = new EncodeUtil();
        util.doCopy("E:\\f1", "utf-8", "gbk");
    }

    private void doCopy(String baseDir, String originalCharsetName, String targetCharsetName)
    {
        System.out.println(baseDir.lastIndexOf("\\" + 1));
        repStr = baseDir.substring(baseDir.lastIndexOf("\\") + 1);
        System.out.println("repStr->" + repStr);
        makeCopyDir(baseDir);
        traceFile(baseDir, originalCharsetName, targetCharsetName);
    }

    private void makeCopyDir(String baseDir)
    {
        Pattern p = Pattern.compile(repStr);//要换掉的子串
        Matcher m = p.matcher(baseDir);//原路径名
        String newDirName = m.replaceFirst(repStr + "Copy");//新子串
        File baseCopyDir = new File(newDirName);
        if (!baseCopyDir.exists())
        {
            baseCopyDir.mkdirs();
        }
    }

    private boolean traceFile(String baseDir, String originalCharsetName, String targetCharsetName)
    {
        File baseDirFile = new File(baseDir);
        File[] fs = baseDirFile.listFiles();
        for (File f : fs)
        {
            if (f.isDirectory())
            {
                Pattern p = Pattern.compile(repStr);
                Matcher m = p.matcher(f.getAbsolutePath());
                String newDirName = m.replaceFirst(repStr + "Copy");

                File descDir = new File(newDirName);
                if (!descDir.exists())
                {
                    descDir.mkdirs();
                }
                System.out.println("旧目录:" + baseDir);
                System.out.println("新目录:" + descDir);
                traceFile(f.getAbsolutePath(), originalCharsetName, targetCharsetName);
            }
            if (f.isFile() && f.getName().endsWith(".txt"))
            {
                Pattern p = Pattern.compile(repStr);
                Matcher m = p.matcher(f.getAbsolutePath());
                String newFileName = m.replaceFirst(repStr + "Copy");
                File descFile = new File(newFileName);
                System.out.println("旧文件:" + baseDir);
                System.out.println("新文件:" + newFileName);
                changeEncode(f, originalCharsetName, targetCharsetName, descFile);
            }
        }
        return true;

    }

    private void changeEncode(File file, String originalCharsetName, String targetCharsetName, File descFile)
    {
        BufferedReader in = null;
        PrintWriter out = null;
        try
        {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(file), originalCharsetName));
            out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(descFile), targetCharsetName), true);
            String str = null;
            while ((str = in.readLine()) != null)
            {
                out.println(str);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                in.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            out.close();
        }
    }
}
