package com.dayu.iorange.utils.http;



import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 功能描述:Http请求工具类
 * 1. http post json 请求
 * 2. http
 *
 * @author: YinShiJun
 * @date: 2018/6/4
 * @version:1.0.0
 * @Copyright (c) 深圳市爱桔科技有限公司-版权所有
 */
public class HttpUtils {

    private final static Logger logger = LoggerFactory.getLogger(HttpUtils.class);


    /**
     * 基于httpclient的 http post json请求
     * @param url:请求地址
     * @param paramsJson：请求参数
     * @return
     */
    public static String doPost(String url ,String paramsJson){
        logger.info(">>>>>> doPost url:{},params:{}",url,paramsJson);
        String response = null ;
        if(StringUtils.isBlank(url) || StringUtils.isBlank(paramsJson)){
            logger.warn("=== doPost params is blank. ");
            return  response;
        }
        // 请求数据处理
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(paramsJson,"utf-8");
        stringEntity.setContentEncoding("UTF-8");
        stringEntity.setContentType("application/json");
        httpPost.setEntity(stringEntity);
        // 返回数据处理
        CloseableHttpResponse httpResponse = null;
        try{
            httpResponse = httpClient.execute(httpPost);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if(statusCode != HttpStatus.SC_OK){
                logger.warn("=== httpPost failure： status_code = {}",statusCode);
                return  response;
            }
            HttpEntity entityReponse = httpResponse.getEntity();
            response = EntityUtils.toString(entityReponse,"UTF-8");
        }catch (Exception e){
            logger.error("=== httpPost Exception:{}",e);
        }finally {
            // 释放连接
            try {
                if(httpResponse != null){
                    httpResponse.close();
                }
                if(httpClient != null){
                    httpClient.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        logger.info(">>>>>> doPost response:{}",response);
        return  response;
    }







}
