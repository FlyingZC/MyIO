package com.zc.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 使用Buffer读写数据一般遵循以下四个步骤：
 * 写入数据到Buffer
 * 调用flip()方法
 * 从Buffer中读取数据
 * 调用clear()方法或者compact()方法
 * 
 * flip()方法将Buffer从写模式切换到读模式。
 * 一旦读完了所有的数据，就需要清空缓冲区，让它可以再次被写入。有两种方式能清空缓冲区：调用clear()或compact()方法。clear()方法会清空整个缓冲区。
 * compact()方法只会清除已经读过的数据。任何未读的数据都被移到缓冲区的起始处，新写入的数据将放到缓冲区未读数据的后面。
 * @author zc
 */
public class T02Buffer
{
    public static void main(String[] args) throws IOException
    {
        RandomAccessFile file = new RandomAccessFile("hello.txt", "rw");
        FileChannel channel = file.getChannel();// 获取channel
        ByteBuffer buffer = ByteBuffer.allocate(24);
        int bytesRead;
        while ((bytesRead = channel.read(buffer)) != -1) // 由channel 读取到buffer
        {
            System.out.println("channel读取大小:" + bytesRead);
            System.out.println("buffer的capacity:" + buffer.capacity());
            System.out.println("buffer的position:" + buffer.position());
            System.out.println("buffer的limit:" + buffer.limit());
            buffer.flip();// 将buffer从 写模式 切换到 读模式
            while (buffer.hasRemaining())
            {//capacity为分配的大小,此处固定为24.limit()为可用大小,如第二次读取了9个字节,则最大为9...
                System.out.println("buffer的capacity:" + buffer.capacity());
                System.out.println("buffer的position:" + buffer.position());
                System.out.println("buffer的limit:" + buffer.limit());
                /*if(buffer.position()==8){
                	buffer.mark();
                }
                if(buffer.position()==9){
                	buffer.reset();
                }*/
                System.out.println(buffer.get());
                //方法只会清除已经读过的数据.如果清空,又从头开始
                //buffer.compact();
            }
            buffer.clear();
        }
        file.close();
    }
}
