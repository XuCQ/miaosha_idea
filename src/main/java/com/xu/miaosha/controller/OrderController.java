package com.xu.miaosha.controller;

import com.xu.miaosha.Vo.GoodsVo;
import com.xu.miaosha.Vo.OrderDetailVo;
import com.xu.miaosha.domain.Goods;
import com.xu.miaosha.domain.MiaoshaUser;
import com.xu.miaosha.domain.OrderInfo;
import com.xu.miaosha.redis.RedisService;
import com.xu.miaosha.result.CodeMsg;
import com.xu.miaosha.result.Result;
import com.xu.miaosha.service.GoodsService;
import com.xu.miaosha.service.MiaoshaUserService;
import com.xu.miaosha.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: miaosha_idea
 * @description: 订单控制
 * @author: Xu Changqing
 * @create: 2020-04-25 11:38
 **/
@Controller
@RequestMapping("/order")
public class OrderController {
    private static Logger log = LoggerFactory.getLogger(GoodController.class);
    @Autowired
    MiaoshaUserService miaoshaUserService;
    @Autowired
    RedisService redisService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderService orderService;

    @RequestMapping("/detail")
    @ResponseBody
    public Result<OrderDetailVo> info(Model model, MiaoshaUser user, @RequestParam("orderId") long orderId) {
        if (user == null) {
//            log.info(Result.error(CodeMsg.SERVER_ERROR).getClass().toString());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
        OrderInfo order = orderService.getOrderById(orderId);
        if (order == null) {
            return Result.error(CodeMsg.ORDER_NOT_EXIST);
        }
        long goodsId = order.getGoodsId();
        GoodsVo goods = goodsService.getGoodVoByGoodsId(goodsId);
        OrderDetailVo orderDetailVo = new OrderDetailVo();
        orderDetailVo.setGoods(goods);
        orderDetailVo.setOrderInfo(order);
        return Result.success(orderDetailVo);
    }
}
