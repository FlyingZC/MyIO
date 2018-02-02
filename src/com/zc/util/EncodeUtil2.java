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

public class EncodeUtil2
{
    private String originalDirName;

    @Test
    public void testChangeEncode()
    {
        changeEncode(new File("E:\\a.txt"), "utf-8", "gbk", new File("E:\\acopy.txt"));
    }

    public static void main(String[] args)
    {
        EncodeUtil2 util = new EncodeUtil2();
        util.doCopy("E:\\f1", "utf-8", "gbk");
    }

    public void doCopy(String baseDir, String originalCharsetName, String targetCharsetName)
    {
        System.out.println(baseDir.lastIndexOf("\\" + 1));
        originalDirName = baseDir.substring(baseDir.lastIndexOf("\\") + 1);
        System.out.println("repStr->" + originalDirName);
        makeCopyDir(baseDir);
        traceFile(baseDir, originalCharsetName, targetCharsetName);
    }

    private void makeCopyDir(String baseDir)
    {
        baseDir.replace(originalDirName,originalDirName+"Copy" );
        Pattern p = Pattern.compile(originalDirName);
        Matcher m = p.matcher(baseDir);
        String newDirName = m.replaceFirst(originalDirName + "Copy");
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
                Pattern p = Pattern.compile(originalDirName);
                Matcher m = p.matcher(f.getAbsolutePath());
                String newDirName = m.replaceFirst(originalDirName + "Copy");

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
                Pattern p = Pattern.compile(originalDirName);
                Matcher m = p.matcher(f.getAbsolutePath());
                String newFileName = m.replaceFirst(originalDirName + "Copy");
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
