package com.zc.util;

import java.io.File;
import java.io.FileFilter;

//批量重命名
public class BatchRename
{
    private static final String folder = "C:\\Users\\Administrator\\Documents\\Zapya\\Photo\\20161211_1054";

    private static final String filePrefix = "IMG_20161210_";

    public static void main(String[] args)
    {
        File root = new File(folder);
        File[] fs = root.listFiles(new FileFilter()
        {
            @Override
            public boolean accept(File pathname)
            {
                return pathname.getName().startsWith(filePrefix);
            }
        });
        int i = 64;
        StringBuilder sb = new StringBuilder();
        for (File f : fs)
        {
            System.out.println(f.getName());
            if (i < 100)
            {
                sb.delete(0, sb.length()).append("0").append(i);
            }
            else
            {
                sb.delete(0, sb.length()).append(i);
            }
            //重命名后源文件将消失,注意加后缀.且输出目录应该存在
            f.renameTo(new File("C:\\out\\" + sb.toString() + ".jpg"));
            i++;
        }
    }
}
