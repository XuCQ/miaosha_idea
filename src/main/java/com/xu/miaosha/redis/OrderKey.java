package com.xu.miaosha.redis;

/**
 * @program: miaosha_idea
 * @description: 获取订单key
 * @author: Xu Changqing
 * @create: 2020-04-09 11:49
 **/
public class OrderKey extends BasePrefix {
    public OrderKey(String prefix) {
        super(prefix);
    }

    public OrderKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }


    public static OrderKey getMiaoshaOrderByUidGid = new OrderKey("moug");
}
