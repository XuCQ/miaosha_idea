package com.xu.miaosha.util;

import java.util.UUID;

/**
 * @program: miaosha_idea
 * @description: 生成cookies
 * @author: Xu Changqing
 * @create: 2020-04-12 18:40
 **/
public class UUIDUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
