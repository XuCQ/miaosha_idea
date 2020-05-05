package com.xu.miaosha.redis;

/**
 * @program: miaosha_idea
 * @description: 秒杀key
 * @author: Xu Changqing
 * @create: 2020-04-28 21:49
 **/
public class MiaoshaKey extends BasePrefix {
    public static MiaoshaKey isGoodsOver = new MiaoshaKey("go");
    public static MiaoshaKey getMiaoshaPath = new MiaoshaKey(60,"mp");
    public static MiaoshaKey getMiaoshaVerifyCode = new MiaoshaKey(300,"vc");

    public MiaoshaKey(String prefix) {
        super(prefix);
    }

    public MiaoshaKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
}
