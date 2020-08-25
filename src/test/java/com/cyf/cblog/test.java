package com.cyf.cblog;

import com.cyf.cblog.util.MD5Util;

/**
 * @Author chenyuanfu
 * @Date 2020/8/6 10:55
 **/
public class test {

    public static void main(String[] args){
        System.out.println(MD5Util.MD5Encode("123456", "UTF-8"));
        //a66abb5684c45962d887564f08346e8d
        System.out.println(MD5Util.MD5Encode("admin123456", "UTF-8"));
    }
}
