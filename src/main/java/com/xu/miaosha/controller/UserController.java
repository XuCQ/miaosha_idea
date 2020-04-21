package com.xu.miaosha.controller;

import com.xu.miaosha.domain.MiaoshaUser;
import com.xu.miaosha.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: miaosha_idea
 * @description: 获取个人用户信息-压测用
 * @author: Xu Changqing
 * @create: 2020-04-21 16:50
 **/
@Controller
@RequestMapping("/user")
public class UserController {
    @ResponseBody
    @RequestMapping("/info")
    public Result<MiaoshaUser> info(Model model, MiaoshaUser user) {
        return Result.success(user);
    }
}
