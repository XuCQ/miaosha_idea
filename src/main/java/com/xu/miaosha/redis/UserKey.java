package com.xu.miaosha.redis;

/**
 * @program: miaosha_idea
 * @description: 获取UserKey
 * @author: Xu Changqing
 * @create: 2020-04-09 11:48
 **/
public class UserKey extends BasePrefix {

    private UserKey(String prefix) {
        super(0, prefix);
    }

    private UserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");


}
