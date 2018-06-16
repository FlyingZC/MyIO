package com.zc.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 使用FileChannel读取数据到Buffer中
 * @author zc
 *
 */
public class T01FileChannel
{
    public static void main(String[] args) throws IOException
    {
        RandomAccessFile file = new RandomAccessFile("hello.txt", "rw");
        //通过file获取 文件管道
        FileChannel inChannel = file.getChannel();
        //创建48个字节的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead;
        //读取数据到 channel中,返回值为一次读取的长度,如第一次48,最后一次可能不足48
        while ((bytesRead = inChannel.read(buf)) != -1)
        {
            System.out.println("读取:" + bytesRead);
            //使缓冲区为一系列新的通道写入或相对获取 操作做好准备：它将限制设置为当前位置，然后将位置设置为 0
            //首先读取数据到Buffer，然后反转Buffer,接着再从Buffer中读取数据。
            buf.flip();// 反转此缓冲区。从读取到buffer 反转到  从buffer中读取 
            while (buf.hasRemaining())// 告知在当前位置和限制之间是否有元素。 当且仅当此缓冲区中至少还有一个元素时返回 true
            {//从buffer中取数据
                System.out.println((char) buf.get());
            }
            buf.clear();
        }
        file.close();
    }
}
