package com.xu.miaosha.Vo;

import com.xu.miaosha.domain.Goods;
import com.xu.miaosha.domain.OrderInfo;

/**
 * @program: miaosha_idea
 * @description:
 * @author: Xu Changqing
 * @create: 2020-04-25 11:40
 **/
public class OrderDetailVo {
    private Goods goods;
    private OrderInfo orderInfo;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    @Override
    public String toString() {
        return "OrderDetailVo{" +
                "goods=" + goods +
                ", orderInfo=" + orderInfo +
                '}';
    }
}
