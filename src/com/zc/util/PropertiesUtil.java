package com.zc.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

public class PropertiesUtil
{
    private static Map<String, String> cachMap = new HashMap<String, String>();

    static
    {
        Properties props = new Properties();
        try
        {
            String rootPath = PropertiesUtil.class.getClassLoader().getResource("//").getPath();
            props.load(new FileInputStream(rootPath + "oracle.properties"));
            for (Map.Entry<Object, Object> every : props.entrySet())
            {
                cachMap.put((String) every.getKey(), (String) every.getValue());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key)
    {
        return cachMap.get(key);
    }

    @Test
    public void test()
    {
        System.out.println(PropertiesUtil.getProperty("driverClass"));
    }
}
