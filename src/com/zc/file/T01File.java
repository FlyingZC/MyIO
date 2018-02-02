package com.zc.file;

import java.io.File;
import java.io.FileFilter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.junit.Test;

//文件,文件夹操作
public class T01File
{
    public static void main(String[] args)
    {
        //.相当于工程的根目录
        File baseDir = new File(".");
        //list()方法返回String[]:list()与listFiles()的区别,
        //前者返回String[],后者返回File[]
        //二者都返回当前目录下的子目录和文件
        for (String f : baseDir.list())
        {
            System.out.println(f);
        }
        for (File file : baseDir.listFiles())
        {
            System.out.println(file);
        }
    }

    //listFiles()
    @Test
    public void testListFile()
    {
        File file = new File(".");
        //File[] listFiles()
        File[] fs = file.listFiles();
        for (File f : fs)
        {
            String name = f.getName();
            //正则表达式做的全匹配
            if (name.matches("^\\.\\w*"))
            {
                System.out.println("点文件" + f.getName());
            }
            //	System.out.println(f.getName());
        }
    }

    //FileFilter类,重写accept方法
    @Test
    public void testFileFilter()
    {
        File file = new File(".");
        //File[] listFiles(FileFilter filter);
        File[] fs = file.listFiles(new FileFilter()
        {
            @Override
            public boolean accept(File pathname)
            {
                return pathname.getName().startsWith(".");
            }
        });

        for (File f : fs)
        {
            System.out.println(f.getName());
        }

    }

    @Test
    public void testFileFilter2()
    {
        File baseFileArray = new File(".");
        //String regexp = "\\.git\\w*";
        //java默认是全匹配啊,.只代表一个字符  .*代表任意多个字符
        String regexp = ".*";
        for (File file : baseFileArray.listFiles(new RegExpFileFilter(regexp)))
        {
            System.out.println(file.getName());
        }
    }

    @Test
    public void testLastModify()
    {
        File file = new File("hello.txt");
        long modify = file.lastModified();
        Date date = new Date();
        date.setTime(modify);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d H:m:s");
        System.out.println(sdf.format(date));
    }
}

/**
 * 接收正则表达式,过滤文件名
 * @author Flyingzc
 *
 */
class RegExpFileFilter implements FileFilter
{
    private Pattern pattern;

    public RegExpFileFilter(String regex)
    {
        pattern = Pattern.compile(regex);
    }

    @Override
    public boolean accept(File pathname)
    {
        return pattern.matcher(pathname.getName()).matches();
    }

}
