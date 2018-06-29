package com.dayu.iorange.utils.file;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 功能描述:文件操作工具类
 *
 * @author: YinShiJun
 * @date: 2018/6/5
 * @version:1.0.0
 * @Copyright (c) 深圳市爱桔科技有限公司-版权所有
 */
public class FileUtils {

    private final static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 判断指定路径下的文件是否存在
     * @param filePath
     * @return
     */
    public static boolean isFileExist(String filePath){
        logger.info(">>> filePath:{}",filePath);
        boolean result = false ;
        if(StringUtils.isBlank(filePath)){
            logger.warn("=== param(filePath) is blank.");
            return  result ;
        }
        File file  = new File(filePath);
        result = file.exists() && file.isFile();
        logger.info(">>> result:{}",result);
        return  result;
    }

    /**
     * 文件目录是否存在，若不存在则创建
     * @param filePath:文件路径
     * @return
     */
    public static boolean makeDirs(String filePath){
        logger.info(">>> filePath:{}",filePath);
        if(StringUtils.isBlank(filePath)){
            logger.warn("=== param(filePath) is blank.");
            return  false;
        }
        // 传递的是文件地址，获取目录地址，若不存在则创建
        int position = filePath.lastIndexOf(File.separator);
        String dirsPath = filePath.substring(0,position);
        logger.info("=== dirsPath:{}",dirsPath);
        File file = new File(dirsPath);
        if(!file.isDirectory()){
            file.mkdirs();
        }
        return  true;
    }


    /**
     *
     * 以字节流的形式读取文件(二进制文件：视频、音频、txt文件等)
     * 通过BufferedInputStream直接可以从内存，减少磁盘IO次数
     * byte[] -> str 数组转字符串 new String(result,"GBK")
     * str -> byte[] str.getBytes("UTF-8")
     * GB2312 表示简体中文字符集 GBK 表示简体繁体字符集
     *
     * @param filePath
     * @return
     */
    public static byte[] readFile(String filePath){
        logger.info(">>> filePath:{}",filePath);
        byte[] result = null;
        if(StringUtils.isBlank(filePath)){
            logger.warn("=== param(filePath) is blank.");
            return  null;
        }
        File file = new File(filePath);
        if(!file.exists()){
            logger.warn("=== file not exists.");
            return  null;
        }
        InputStream ins = null;
        BufferedInputStream bins = null;
        try {
            ins = new FileInputStream(file);
            bins = new BufferedInputStream(ins);
            ByteArrayOutputStream boutStream = new ByteArrayOutputStream();
            // 定义字节读取缓存区
            byte[] buffer = new byte[1024*8];
            int length = 0;
            while ((length = bins.read(buffer)) != -1){
                boutStream.write(buffer,0,length);
            }
            result = boutStream.toByteArray();
            boutStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (bins != null) {
                    bins.close();
                }
                if (ins != null) {
                    ins.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        logger.info(">>> result.length = {}",result==null?0:result.length);
        return  result;
    }





    public static void main(String args[]){
        String filePath = "C:\\Users\\xn048817\\Desktop\\CreditorRightServiceImpl.java";
        readFile(filePath);
    }



}
