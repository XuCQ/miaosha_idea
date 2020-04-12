package com.xu.miaosha.controller;

import com.xu.miaosha.Vo.LoginVo;
import com.xu.miaosha.domain.MiaoshaUser;
import com.xu.miaosha.result.Result;
import com.xu.miaosha.service.MiaoshaUserService;
import com.xu.miaosha.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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


    /**
     * @param model
     * @param cookieToken
     * @param paramToken
     * @return
     * @RequestParam(value = COOKI_NAME_TOKEN) String paramToken: 有些手机端不放在cookies中
     */
    @RequestMapping("/to_list")
    public String toList(Model model, MiaoshaUser user) {
        model.addAttribute("user", user);
        return "goods_list";
    }

}
