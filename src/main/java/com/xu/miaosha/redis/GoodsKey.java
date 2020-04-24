package com.xu.miaosha.redis;

/**
 * @program: miaosha_idea
 * @description: redis-Goods 存储
 * @author: Xu Changqing
 * @create: 2020-04-24 21:13
 **/
public class GoodsKey extends BasePrefix {


    public static GoodsKey getGoodsList = new GoodsKey(60, "gl");
    public static GoodsKey getGoodsDetail = new GoodsKey(60, "gd");

    public GoodsKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
}
