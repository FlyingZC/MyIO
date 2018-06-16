package com.zc.file;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class T04FileTree
{
    @Test
    public void test()
    {
        System.out.println(new FileTree().walk("."));
    }
}

/**
 * @author Flyingzc
 * 文件树
 */
class FileTree implements Iterable<File>
{
    private List<File> dirs = new ArrayList<File>();

    private List<File> files = new ArrayList<File>();

    @Override
    public Iterator<File> iterator()
    {
        //返回的是files的迭代器
        return files.iterator();
    }

    public void addAll(FileTree other)
    {
        dirs.addAll(other.dirs);
        files.addAll(other.files);
    }

    public FileTree walk(String baseDir)
    {
        return walk(baseDir, ".*");
    }

    public FileTree walk(String baseDir, String regexp)
    {
        return trace(new File(baseDir), regexp);
    }

    private FileTree trace(File baseDir, String regexp)
    {
        FileTree tree = new FileTree();
        for (File file : baseDir.listFiles(new RegExpFileFilter(regexp)))
        {
            if (file.isDirectory())
            {
                tree.dirs.add(file);
                tree.addAll(trace(file, regexp));
            }
            else if (file.isFile())
            {
                tree.files.add(file);
            }
        }
        return tree;
    }

    @Override
    public String toString()
    {
        return dirs.toString() + files.toString();
    }
}
