package com.xu.miaosha.rabbitmq;

import com.xu.miaosha.domain.MiaoshaUser;

/**
 * @program: miaosha_idea
 * @description: 秒杀订单信息，用作mq
 * @author: Xu Changqing
 * @create: 2020-04-28 18:50
 **/
public class MiaoshaMessage {
    private MiaoshaUser user;
    private long goodsId;

    public MiaoshaUser getUser() {
        return user;
    }

    public void setUser(MiaoshaUser user) {
        this.user = user;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }
}
