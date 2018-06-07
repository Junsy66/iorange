package com.dayu.iorange.utils.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

/**
 * 功能描述:属性文件(.properties)操作工具类
 *
 * @author: YinShiJun
 * @date: 2018/6/7
 * @version:1.0.0
 * @Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有
 */
public class PropertiesUtils {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

    /**
     * Properties 对象继承 hashTable
     * @param propertiesPath
     * @return
     */
    public static Properties getProperties(String propertiesPath){
        Properties properties = new Properties();
        InputStream is = null;
        try {
            is = new BufferedInputStream(new FileInputStream(new File(propertiesPath)));
            properties.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(is != null){
                try {
                    is.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return  properties;
    }


    public static void main(String args[]){

    }

}
