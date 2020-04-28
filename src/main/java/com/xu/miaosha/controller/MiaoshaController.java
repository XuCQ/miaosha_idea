package com.xu.miaosha.controller;

import com.xu.miaosha.Vo.GoodsVo;
import com.xu.miaosha.domain.Goods;
import com.xu.miaosha.domain.MiaoshaOrder;
import com.xu.miaosha.domain.MiaoshaUser;
import com.xu.miaosha.domain.OrderInfo;
import com.xu.miaosha.rabbitmq.MQSender;
import com.xu.miaosha.rabbitmq.MiaoshaMessage;
import com.xu.miaosha.redis.GoodsKey;
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
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: miaosha_idea
 * @description: 秒杀功能 Controller
 * @author: Xu Changqing
 * @create: 2020-04-20 21:07
 **/
@Controller
@RequestMapping("/miaosha")
public class MiaoshaController implements InitializingBean {
    private static Logger log = LoggerFactory.getLogger(MiaoshaController.class);
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
    @Autowired
    MQSender mqSender;

    //标记库存是否为空，false不为空
    private Map<Long, Boolean> localOverMap = new HashMap<Long, Boolean>();

    /**
     * 系统初始化
     * 容器启动时加载，把库存放到redis中
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        List<GoodsVo> goodsVos = goodsService.listGoodsVo();
        if (goodsVos == null) {
            return;
        }
        // 把每个商品的库存数量初始化到redis中
        for (GoodsVo goodsVo : goodsVos) {
            redisService.set(GoodsKey.getMiaoshaGoodsStock, "" + goodsVo.getId(), goodsVo.getStockCount());
            localOverMap.put(goodsVo.getId(), false);
        }
//        log.info("InitializingBean Done!");
    }

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
    public Result<Integer> doMiaosha(Model model, MiaoshaUser user, @RequestParam("goodsId") long goodsId) {
        model.addAttribute("user", user);
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        // 内存标记，减少redis访问
        Boolean over = localOverMap.get(goodsId);
        if (over) {
            return Result.error(CodeMsg.MIAO_SHA_OVER);
        }
        // 预减库存
        long stock = redisService.decr(GoodsKey.getMiaoshaGoodsStock, "" + goodsId);
        // 无库存
        if (stock < 0) {
            localOverMap.put(goodsId, true);
            return Result.error(CodeMsg.MIAO_SHA_OVER);
        }
        //判断是否秒杀成功
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
        if (order != null) {
            return Result.error(CodeMsg.REPEATE_MIAO_SHA);
        }
//        log.info("doMiaosha test");
        // 入队
        MiaoshaMessage miaoshaMessage = new MiaoshaMessage();
        miaoshaMessage.setUser(user);
        miaoshaMessage.setGoodsId(goodsId);
        mqSender.sendMiaoshaMessage(miaoshaMessage);
        // 返回0，表示排队中
        return Result.success(0);
        /**
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
         */
    }

    /**
     * 秒杀成功后，后端轮询
     * orderId:成功
     * -1：秒杀失败
     * 0：排队中
     */
    @RequestMapping(value = "/result", method = RequestMethod.GET)
    @ResponseBody
    public Result<Long> miaoshaResult(Model model, MiaoshaUser user, @RequestParam("goodsId") long goodsId) {
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        long result = miaoshaService.getMiaoshaResult(user.getId(), goodsId);
        return Result.success(result);
    }
}
