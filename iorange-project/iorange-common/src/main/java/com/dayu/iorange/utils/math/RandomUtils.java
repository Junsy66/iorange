package com.dayu.iorange.utils.math;

import java.util.HashSet;
import java.util.Random;

/**
 * 功能描述:随机数生成工具类
 *
 * @author: YinShiJun
 * @date: 2018/6/1
 * @version:1.0.0
 * @Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有
 */
public class RandomUtils {


    /**
     * 生成范围内的随机整数;范围为[min,max]
     * @param min
     * @param max
     * @return
     */
    public static int generateInRange(int min,int max){
        int randomNum = 0;
        Random random = new Random();
        randomNum = random.nextInt(max-min+1) + min ;
        return  randomNum;
    }


    /**
     * 获取范围内[min,max]不重复的n个数
     * @param min
     * @param max
     * @param n
     * @return
     */
    public static HashSet<Integer> generateInRange(int min,int max,int n){
        if(min>max || n>(max-min+1)){
            return null;
        }
        HashSet<Integer> randomSet = new HashSet<>();
        do{
            int randomNum = generateInRange(min,max);
            randomSet.add(randomNum);
        } while (randomSet.size() < n);
        return  randomSet;
    }





    public static void main(String args[]){

        HashSet<Integer> set = generateInRange(1,10,9);
        for(Integer item :set){
            System.out.println(item);
        }
    }


}
