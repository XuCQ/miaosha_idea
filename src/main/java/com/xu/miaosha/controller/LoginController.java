package com.xu.miaosha.controller;

import com.xu.miaosha.result.Result;
import com.xu.miaosha.Vo.LoginVo;
import com.xu.miaosha.service.MiaoshaUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


/**
 * @program: miaosha_idea
 * @description: 用户登陆
 * @author: Xu Changqing
 * @create: 2020-04-11 22:15************
 **/
@Controller
@RequestMapping("/login")
public class LoginController {
    private static Logger log = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    MiaoshaUserService miaoshaUserService;

    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
        log.info(loginVo.toString());
//        // 参数校验
//        String password = loginVo.getPassword();
//        String mobile = loginVo.getMobile();
//        if(StringUtils.isEmpty(password)){
//            return Result.error(CodeMsg.PASSWORD_EMPTY);
//        }
//        if(StringUtils.isEmpty(mobile)){
//            return Result.error(CodeMsg.MOBILE_EMPTY);
//        }
//        if(!ValidatorUtil.isMobile(mobile)){
//            return Result.error(CodeMsg.MOBILE_ERROR);
//        }
        // 登陆
//        CodeMsg cm = miaoshaUserService.login(loginVo);
//        if (cm.getCode()==0){
//            return Result.success(true);
//        }
//        else{
//            return Result.error(cm);
//        }
        miaoshaUserService.login(response, loginVo);
        return Result.success(true);
    }

}
