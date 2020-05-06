package com.xu.miaosha.config;

import com.xu.miaosha.domain.MiaoshaUser;

/**
 * @program: miaosha_idea
 * @description: 使用ThreadLocal存储用户信息
 * @author: Xu Changqing
 * @create: 2020-05-06 17:10
 **/
public class UserContext {
    private static ThreadLocal<MiaoshaUser> userHolder = new ThreadLocal<MiaoshaUser>();

    public static void setUser(MiaoshaUser user) {
        userHolder.set(user);
    }

    public static MiaoshaUser getUser() {
        return userHolder.get();
    }

}
