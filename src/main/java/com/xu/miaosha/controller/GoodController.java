package com.xu.miaosha.controller;

import com.xu.miaosha.Vo.GoodsVo;
import com.xu.miaosha.Vo.LoginVo;
import com.xu.miaosha.domain.MiaoshaUser;
import com.xu.miaosha.redis.RedisService;
import com.xu.miaosha.result.Result;
import com.xu.miaosha.service.GoodsService;
import com.xu.miaosha.service.MiaoshaUserService;
import com.xu.miaosha.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.util.List;

import static com.xu.miaosha.service.MiaoshaUserService.COOKI_NAME_TOKEN;


/**
 * @program: miaosha_idea
 * @description: 商品页面
 * @author: Xu Changqing
 * @create: 2020-04-11 22:15************
 **/
@Controller
@RequestMapping("/goods")
public class GoodController {
    private static Logger log = LoggerFactory.getLogger(GoodController.class);
    @Autowired
    MiaoshaUserService miaoshaUserService;
    @Autowired
    RedisService redisService;
    @Autowired
    GoodsService goodsService;


    /**
     * @param model
     * @param cookieToken
     * @param paramToken
     * @return
     * @RequestParam(value = COOKI_NAME_TOKEN) String paramToken: 有些手机端不放在cookies中
     * 压测：2020-04-21 22:12:11 600QPS 5000并发*10
     */
    @RequestMapping("/to_list")
    public String toList(Model model, MiaoshaUser user) {

        model.addAttribute("user", user);
        //查询商品列表
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
//        log.info(user.toString());
        model.addAttribute("goodsList", goodsList);
        return "goods_list";
    }

    @RequestMapping("/to_detail/{goodsId}")
    public String toDetail(Model model, MiaoshaUser user, @PathVariable("goodsId") long goodsId) {
        model.addAttribute("user", user);
        // goodsId 一般不用UUID，用雪花算法
        GoodsVo goods = goodsService.getGoodVoByGoodsId(goodsId);
        model.addAttribute("goods", goods);
        //
        long startAt = goods.getStartDate().getTime();
        long endAt = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();
        int remainSeconds = 0;
        int miaoshaStatus = 0;
        if (now < startAt) {
            //秒杀还没开始
            miaoshaStatus = 0;
            remainSeconds = (int) ((startAt - now) / 1000);
        } else if (now > endAt) {
            //秒杀已经结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        } else {
            //秒杀中
            miaoshaStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("remainSeconds", remainSeconds);
        model.addAttribute("miaoshaStatus", miaoshaStatus);
        return "goods_detail";
    }

}
