package com.xu.miaosha.controller;

import com.xu.miaosha.domain.User;
import com.xu.miaosha.redis.RedisService;
import com.xu.miaosha.redis.UserKey;
import com.xu.miaosha.result.CodeMsg;
import com.xu.miaosha.result.Result;
import com.xu.miaosha.service.UserService;
import org.omg.IOP.Codec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @program: miaosha_idea
 * @description: 控制器
 * @author: Xu Changqing
 * @create: 2020-04-08 16:35
 **/

/**
 * @author Xu Changqing
 * @RestController 注解相当于@ResponseBody ＋ @Controller合在一起的作用。
 * RestController使用的效果是将方法返回的对象直接在浏览器上展示成json格式，而如果单单使用@Controller会报错，需要ResponseBody配合使用。
 */
@Controller
@RequestMapping("/demo")
public class SampleController {
    @Autowired
    UserService userService;
    @Autowired
    RedisService redisService;

    /**
     * 页面输出
     *
     * @param model
     * @return 模板名称
     */
    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "xu");
        return "hello";
    }

    /**
     * rest api json 输出
     *
     * @return void
     */
    @RequestMapping("/hello")
    @ResponseBody
    public Result<String> hello() {
        return Result.success("hello xu");
    }


    @RequestMapping("/helloError")
    @ResponseBody
    public Result<String> helloError() {
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    /**
     * 返回页面模板
     *
     * @return template文件夹下模板名称
     */
    @RequestMapping("/db/get")
    @ResponseBody
    public Result dbGet() {
        User user = userService.getById(1);
        return Result.success(user);
    }

    @RequestMapping("/db/tc")
    @ResponseBody
    public Result<Boolean> dbTx() {
        userService.tx();
        return Result.success(true);

    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet() {
        User user = redisService.get(UserKey.getById,""+1, User.class);
        return Result.success(user);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet() {
        User user = new User(1, "Changqing");
        Boolean aBoolean = redisService.set(UserKey.getById,""+user.getId(),user);
        return Result.success(aBoolean);
    }

}
