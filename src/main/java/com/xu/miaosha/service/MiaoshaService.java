package com.xu.miaosha.service;

import com.xu.miaosha.Vo.GoodsVo;
import com.xu.miaosha.dao.GoodsDao;
import com.xu.miaosha.dao.OrderDao;
import com.xu.miaosha.domain.Goods;
import com.xu.miaosha.domain.MiaoshaUser;
import com.xu.miaosha.domain.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: miaosha_idea
 * @description: 秒杀事务
 * @author: Xu Changqing
 * @create: 2020-04-20 22:23
 **/
@Service
public class MiaoshaService {
    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderService orderService;

    /**
     * miaosha操作，事务（原子）
     *
     * @param user
     * @param goods
     * @return
     */
    @Transactional
    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods) {
        //减库存
        goodsService.reduceStock(goods);
        //下订单:order_info miaosha_order
        return orderService.createOrder(user, goods);
    }
}
