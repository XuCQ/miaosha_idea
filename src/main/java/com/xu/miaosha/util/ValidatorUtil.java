package com.xu.miaosha.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: miaosha_idea
 * @description: 手机号格式验证
 * @author: Xu Changqing
 * @create: 2020-04-12 02:01
 **/
public class ValidatorUtil {
    private static final Pattern mobile_patter = Pattern.compile("1\\d{10}");
    private static Logger log = LoggerFactory.getLogger(ValidatorUtil.class);

    public static boolean isMobile(String src) {
//        log.info(src);
        if (StringUtils.isEmpty(src)) {
            return false;
        }
        Matcher matcher = mobile_patter.matcher(src);
        return matcher.matches();
    }


    public static void main(String[] args) {
        System.out.println(isMobile("15852911871"));
        System.out.println(isMobile("1234567891"));
        System.out.println(isMobile("1234567891234"));
        System.out.println(isMobile("23456789123"));

    }
}
