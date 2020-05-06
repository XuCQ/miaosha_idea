package com.xu.miaosha.redis;

/**
 * @program: miaosha_idea
 * @description: 访问次数key
 * @author: Xu Changqing
 * @create: 2020-04-28 21:49
 **/
public class AccessKey extends BasePrefix {
    public static AccessKey access = new AccessKey(5,"access");
    public static AccessKey withExpire(int expireSecond){
        return new AccessKey(expireSecond,"access");
    }

    public AccessKey(String prefix) {
        super(prefix);
    }

    public AccessKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
}
