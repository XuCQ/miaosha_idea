package com.xu.miaosha.Vo;

import com.xu.miaosha.domain.MiaoshaUser;

/**
 * @program: miaosha_idea
 * @description: 商品详情Vo
 * @author: Xu Changqing
 * @create: 2020-04-25 00:08
 **/
public class GoodsDetailVo {
    private int remainSeconds = 0;
    private int miaoshaStatus = 0;
    private GoodsVo goods;
    private MiaoshaUser user;

    public MiaoshaUser getUser() {
        return user;
    }

    public void setUser(MiaoshaUser user) {
        this.user = user;
    }

    public int getRemainSeconds() {
        return remainSeconds;
    }

    public void setRemainSeconds(int remainSeconds) {
        this.remainSeconds = remainSeconds;
    }

    public int getMiaoshaStatus() {
        return miaoshaStatus;
    }

    public void setMiaoshaStatus(int miaoshaStatus) {
        this.miaoshaStatus = miaoshaStatus;
    }

    public GoodsVo getGoods() {
        return goods;
    }

    public void setGoods(GoodsVo goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "GoodsDetailVo{" +
                "remainSeconds=" + remainSeconds +
                ", miaoshaStatus=" + miaoshaStatus +
                ", goods=" + goods +
                ", user=" + user +
                '}';
    }
}
