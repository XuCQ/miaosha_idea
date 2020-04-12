package com.xu.miaosha.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @program: miaosha_idea
 * @description: MD5加密
 * @author: Xu Changqing
 * @create: 2020-04-11 22:01
 **/
public class MD5Util {
    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    private static final String salt = "1a2b3c4d";

    public static String inputPassFormPass(String inputPass) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String formPassToDBPass(String fromPass, String salt) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + fromPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }
    public static String inputPassToDBPass(String input, String saltDB){
        String formPass=inputPassFormPass(input);
        return formPassToDBPass(formPass,saltDB);
    }

    public static void main(String[] args) {
        System.out.println(inputPassFormPass("123456"));
        System.out.println(formPassToDBPass(inputPassFormPass("123456"),"1a2b3c4d"));
        System.out.println(inputPassToDBPass("123456","1a2b3c4d"));
    }
}
