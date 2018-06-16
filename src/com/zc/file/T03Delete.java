package com.zc.file;

import java.io.File;

/**
 * @author zc
 * 级联删除所有文件夹
 *
 */
public class T03Delete
{
    /**
     *  delete
    	public boolean delete()
    	删除此抽象路径名表示的文件或目录。如果此路径名表示一个目录，则该目录必须为空才能删除。 
     */
    public static void main(String[] args)
    {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 100; i++)
        {
            builder.append("目录").append(i).append(File.separator);
        }
        File folder = new File(builder.toString());
        if (!folder.exists())
        {
            folder.mkdirs();
        }
        File delDir = new File("目录0");
        del(delDir);
    }

    //级联删除所有空文件夹
    public static void del(File folder)
    {
        if (folder.isDirectory())
        {
            File[] fs = folder.listFiles();
            for (int i = 0; i < fs.length; i++)
            {
                del(fs[i]);
                System.out.println("正在删除:" + fs[i]);
                fs[i].delete();
            }
            folder.delete();
        }
    }
}
