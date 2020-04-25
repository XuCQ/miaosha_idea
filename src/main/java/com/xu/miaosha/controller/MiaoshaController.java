package com.xu.miaosha.controller;

import com.xu.miaosha.Vo.GoodsVo;
import com.xu.miaosha.domain.MiaoshaOrder;
import com.xu.miaosha.domain.MiaoshaUser;
import com.xu.miaosha.domain.OrderInfo;
import com.xu.miaosha.redis.RedisService;
import com.xu.miaosha.result.CodeMsg;
import com.xu.miaosha.result.Result;
import com.xu.miaosha.service.GoodsService;
import com.xu.miaosha.service.MiaoshaService;
import com.xu.miaosha.service.MiaoshaUserService;
import com.xu.miaosha.service.OrderService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: miaosha_idea
 * @description: 秒杀功能 Controller
 * @author: Xu Changqing
 * @create: 2020-04-20 21:07
 **/
@Controller
@RequestMapping("/miaosha")
public class MiaoshaController {
    private static Logger log = LoggerFactory.getLogger(GoodController.class);
    @Autowired
    MiaoshaUserService miaoshaUserService;
    @Autowired
    RedisService redisService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderService orderService;
    @Autowired
    MiaoshaService miaoshaService;

    /**
     * 2020-04-22 02:12:51 压测结果 3992 （5000*10）；
     * 问题：超卖
     * 2020-04-25 17:38:26 压测结果 5000 （5000*10）；
     * 解决超卖
     *
     * @param model
     * @param user
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "/do_miaosha", method = RequestMethod.POST)
    @ResponseBody
    public Result<OrderInfo> doMiaosha(Model model, MiaoshaUser user, @RequestParam("goodsId") long goodsId) {
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        //判断库存
        GoodsVo goods = goodsService.getGoodVoByGoodsId(goodsId);
        int stock = goods.getStockCount();
        if (stock <= 0) {
            return Result.error(CodeMsg.MIAO_SHA_OVER);

        }
        //判断是否秒杀成功
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
        if (order != null) {
            return Result.error(CodeMsg.REPEATE_MIAO_SHA);
        }
        // 执行秒杀：减库存，下订单，写入秒杀订单
        OrderInfo orderInfo = miaoshaService.miaosha(user, goods);
        return Result.success(orderInfo);
    }
}
