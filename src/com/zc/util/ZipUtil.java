package com.zc.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

public class ZipUtil
{

    /** 
     * 执行压缩文件的解压,并自动生成文件的操作 
     * @param in 
     * @param outputDirectory 
     * @return 
     *  
     */
    public static Map<String, Object> unZip(InputStream in, String outputDirectory)
    {

        if (in == null)
            return null;

        ZipEntry zipEntry = null;
        FileOutputStream out = null;
        String uniqueName, iconUrl = null;
        Map<String, Object> map = new HashMap<String, Object>();
        ZipInputStream zipIn = new ZipInputStream(in);
        try
        {
            while ((zipEntry = zipIn.getNextEntry()) != null)
            {
                //如果是文件夹路径方式，本方法内暂时不提供操作  
                if (zipEntry.isDirectory())
                {
                    //                  String name = zipEntry.getName();  
                    //                  name = name.substring(0, name.length() - 1);  
                    //                  File file = new File(outputDirectory + File.separator + name);  
                    //                  file.mkdir();  
                }
                else
                {
                    //如果是文件，则直接在对应路径下生成   
                    uniqueName = getSavedFileName(zipEntry.getName());
                    File path = new File(outputDirectory + File.separator);
                    if (!path.exists())
                        path.mkdirs();

                    iconUrl = outputDirectory + File.separator + uniqueName;
                    File file = new File(iconUrl);
                    file.createNewFile();
                    out = new FileOutputStream(file);
                    int b = 0;
                    while ((b = zipIn.read()) != -1)
                    {
                        out.write(b);
                    }
                    out.close();
                    map.put(zipEntry.getName(), iconUrl);
                }
            }
            return map;
        }
        catch (Exception ex)
        {
            return null;
        }
        finally
        {
            IOUtils.closeQuietly(zipIn);
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        }
    }

    /** 
     * 解压压缩文件流，并根据解压层次来判断，是否只解析第一层，还是解析所有数据 
     * map<name,inputstream> 
     * @param in 
     * @param parseLevel 
     * @return 
     */
    public static Map<String, Object> unZip(InputStream in, int parseLevel)
    {

        if (in == null)
            return null;

        ZipEntry zipEntry = null;
        FileOutputStream out = null;
        Map<String, Object> map = new HashMap<String, Object>();
        ZipInputStream zipIn = new ZipInputStream(in);
        try
        {
            while ((zipEntry = zipIn.getNextEntry()) != null)
            {
                //如果是文件夹路径方式，本方法内暂时不提供操作  
                if (zipEntry.isDirectory())
                {
                    //                  String name = zipEntry.getName();  
                    //                  name = name.substring(0, name.length() - 1);  
                    //                  File file = new File(outputDirectory + File.separator + name);  
                    //                  file.mkdir();  
                }
                else
                {
                    //如果是文件，则直接存放在Map中  
                    String name = zipEntry.getName();
                    //把压缩文件内的流转化为字节数组，够外部逻辑使用(之后关闭流)  
                    byte[] bt = IOUtils.toByteArray(zipIn);
                    map.put(name, bt);
                }
            }
            return map;
        }
        catch (Exception ex)
        {
            return null;
        }
        finally
        {
            IOUtils.closeQuietly(zipIn);
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        }

    }

    /** 
     * 根据传递的文件名(带后缀)生成唯一新文件名(带后缀) 
     * @param origFileName 
     * @return  
     */
    private static String getSavedFileName(String origFileName)
    {
        return FilenameUtils.getExtension(origFileName) + new Date().getTime();
    }

    public static void main(String[] args) throws IOException
    {

        //      FileInputStream in = new FileInputStream(new File("d:\\Temp\\Temp.zip"));  
        //      Map map = unZip(in,0);  
        //      System.out.println("map is " + map.size());  

        //      byte[] bt = (byte[])map.get("ziptest.zip");   
        byte[] bt = null;
        InputStream in1 = new ByteArrayInputStream(bt);
        //      unZip(in1,"d://test");  
        System.err.println("over.");
    }

}
