package com.xu.miaosha.redis;

/**
 * @program: miaosha_idea
 * @description: redis存储用户token
 * @author: Xu Changqing
 * @create: 2020-04-12 18:58
 **/
public class MiaoshaUserKey extends BasePrefix {

    private static final int TOKEN_EXPIRE=3600*24*2;
    public MiaoshaUserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static MiaoshaUserKey token = new MiaoshaUserKey(TOKEN_EXPIRE,"tk");
}
