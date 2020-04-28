package com.xu.miaosha.redis;

/**
 * @program: miaosha_idea
 * @description: 秒杀key
 * @author: Xu Changqing
 * @create: 2020-04-28 21:49
 **/
public class MiaoshaKey extends BasePrefix {
    public static MiaoshaKey isGoodsOver=new MiaoshaKey("go");
    public MiaoshaKey(String prefix) {
        super(prefix);
    }
}
