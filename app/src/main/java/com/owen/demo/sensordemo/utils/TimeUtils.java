package com.owen.demo.sensordemo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    /**
     * 将时间转换为时间戳
     * @param s 需要转化的时间
     * @return 返回时间戳
     * @throws ParseException
     */
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    /**
     * 将时间戳转换为时间
     * @param flag 是否是Unix时间戳
     * @param s 需要转化的时间戳
     * @return 返回格式化的时间
     */
    public static String stampToDate(boolean flag, String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        //Unix时间戳是从1970年1月1日（UTC/GMT的午夜）开始所经过的秒数
        //Java时间戳获取到从1970年1月1日（UTC/GMT的午夜）开始所经过的毫秒数
        if (flag){
            lt = lt * 1000;
        }
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 将时间戳转换为时间
     * @param flag 是否是Unix时间戳
     * @param stamp 需要转化的时间戳
     * @return 返回格式化的时间
     */
    public static String stampToDate(boolean flag, long stamp){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //Unix时间戳是从1970年1月1日（UTC/GMT的午夜）开始所经过的秒数
        //Java时间戳获取到从1970年1月1日（UTC/GMT的午夜）开始所经过的毫秒数
        if (flag){
            stamp = stamp * 1000;
        }
        Date date = new Date(stamp);
        res = simpleDateFormat.format(date);
        return res;
    }
}
