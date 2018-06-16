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

/**
 * 递归目录树 完成文件转码
 * @author zc
 */
public class EncodChangeUtil
{

    /**
     * TODO 1.支持配置不同的文件后缀; 2.支持配置不同的转码方向GBK->UTF-8
     * @param args
     */
    public static void main(String[] args)
    {
        EncodChangeUtil.encode("D:\\myworkspace\\workspace1-my\\MyPattern\\src\\cn\\javass\\dp");
    }

    public static void encode(String srcFullDir)
    {
        EncodeEntity entity = new EncodeEntity(srcFullDir);
        trace(entity, entity.getSrcFile());
        System.out.println("项目转码成功");
    }

    /**
     * 遍历所有文件夹
     */
    public static void trace(EncodeEntity entity, File file)
    {
        if (file.isDirectory())
        {
            File destFile = entity.getDestFile();
            File[] fs = file.listFiles();
            for (File f : fs)
            {
                String srcDirName = f.getAbsolutePath();
                String destDirName = new StringBuilder(srcDirName).replace(entity.getProjectNameStartIdx(),
                        entity.getProjectNameEndIdx() + 1, entity.getDestProjectName()).toString();// (int start, int end, String str)
                destFile = new File(destDirName);
                if (f.isDirectory())
                {
                    if (!destFile.exists())
                    {
                        destFile.mkdirs();
                    }
                    trace(entity, f);
                }
                if (f.isFile())
                {
                    // TODO 以.java结尾的,调用转码方法; 这个判断抽出来
                    if (f.getName().endsWith(".java"))
                    {
                        codeChange(f, destFile);
                        System.out.println(destFile.getAbsolutePath());
                    }
                    else
                    {
                        //其他文件直接复制
                        copyFile(f, destFile);
                    }
                }
            }
        }
    }

    /**
     * 复制普通文件(无需转码的文件)
     * @param src
     * @param dest
     */
    private static void copyFile(File src, File dest)
    {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try
        {
            fis = new FileInputStream(src);
            fos = new FileOutputStream(dest);
            byte[] b = new byte[1024];
            int len = -1;
            while ((len = fis.read(b)) != -1)
            {
                fos.write(b, 0, len);
            }

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (fis != null)
            {
                try
                {
                    fis.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if (fos != null)
            {
                try
                {
                    fos.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 转码
     * @param srcFile
     */
    public static void codeChange(File src, File dest)
    {
        BufferedReader br = null;
        PrintWriter pw = null;
        try
        {
            FileInputStream fis = new FileInputStream(src);
            InputStreamReader isr = new InputStreamReader(fis, "GBK");
            br = new BufferedReader(isr);
            FileOutputStream fos = new FileOutputStream(dest);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
            pw = new PrintWriter(osw, true);
            String str = null;
            while ((str = br.readLine()) != null)
            {
                pw.println(str);
            }

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (br != null)
            {
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if (pw != null)
            {
                pw.close();
            }
        }
    }

    /**
     * @author flyingzc
     * 转码 所需的实体类
     */
    static class EncodeEntity
    {

        public EncodeEntity(String srcFullDir)
        {
            this.srcFullDir = srcFullDir;
            this.projectNameStartIdx = this.srcFullDir.lastIndexOf("\\") + 1;
            this.projectNameEndIdx = this.srcFullDir.length() - 1;
            this.srcProjectName = srcFullDir.substring(projectNameStartIdx);
            this.destProjectName = srcProjectName + "Copy";
            // 复制后的项目根目录名
            this.destFullDir = new StringBuilder(srcFullDir).append("Copy").toString();
            this.srcFile = new File(srcFullDir);
            this.destFile = new File(destFullDir);
        }

        /**
         * 原始资源 根目录
         */
        private String srcFullDir;

        /**
         * 复制后资源 根目录
         */
        private String destFullDir;

        /**
         * 原项目名,如MyChat
         */
        private String srcProjectName;

        /**
         * 转换后的项目名,默认 原项目名 + Copy
         */
        private String destProjectName;

        private File srcFile;

        private File destFile;

        //下面两个属性是原项目名在全路径中的下标,用来产生新的目录
        private int projectNameStartIdx;

        private int projectNameEndIdx;

        public String getSrcFullDir()
        {
            return srcFullDir;
        }

        public void setSrcFullDir(String srcFullDir)
        {
            this.srcFullDir = srcFullDir;
        }

        public String getDestFullDir()
        {
            return destFullDir;
        }

        public void setDestFullDir(String destFullDir)
        {
            this.destFullDir = destFullDir;
        }

        public String getSrcProjectName()
        {
            return srcProjectName;
        }

        public void setSrcProjectName(String srcProjectName)
        {
            this.srcProjectName = srcProjectName;
        }

        public String getDestProjectName()
        {
            return destProjectName;
        }

        public void setDestProjectName(String destProjectName)
        {
            this.destProjectName = destProjectName;
        }

        public File getSrcFile()
        {
            return srcFile;
        }

        public void setSrcFile(File srcFile)
        {
            this.srcFile = srcFile;
        }

        public File getDestFile()
        {
            return destFile;
        }

        public void setDestFile(File destFile)
        {
            this.destFile = destFile;
        }

        public int getProjectNameStartIdx()
        {
            return projectNameStartIdx;
        }

        public void setProjectNameStartIdx(int projectNameStartIdx)
        {
            this.projectNameStartIdx = projectNameStartIdx;
        }

        public int getProjectNameEndIdx()
        {
            return projectNameEndIdx;
        }

        public void setProjectNameEndIdx(int projectNameEndIdx)
        {
            this.projectNameEndIdx = projectNameEndIdx;
        }

    }
}
