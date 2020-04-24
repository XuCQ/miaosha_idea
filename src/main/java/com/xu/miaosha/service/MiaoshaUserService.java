package com.xu.miaosha.service;

import com.xu.miaosha.dao.MiaoshaUserDao;
import com.xu.miaosha.redis.MiaoshaUserKey;
import com.xu.miaosha.redis.RedisService;
import com.xu.miaosha.result.CodeMsg;
import com.xu.miaosha.Vo.LoginVo;
import com.xu.miaosha.domain.MiaoshaUser;
import com.xu.miaosha.exception.GlobalExecption;
import com.xu.miaosha.util.MD5Util;
import com.xu.miaosha.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


/**
 * @program: miaosha_idea
 * @description: 登陆服务
 * @author: Xu Changqing
 * @create: 2020-04-12 02:14
 **/
@Service
public class MiaoshaUserService {
    public static final String COOKI_NAME_TOKEN = "token";
    @Autowired
    MiaoshaUserDao miaoshaUserDao;
    @Autowired
    RedisService redisService;

    public MiaoshaUser getById(long id) {
        // 取缓存   对象缓存思想
        MiaoshaUser user = redisService.get(MiaoshaUserKey.getById, "" + id, MiaoshaUser.class);
        if (user != null) {
            return user;
        }
        // 取数据库
        user = miaoshaUserDao.getById(id);
        if (user != null) {
            redisService.set(MiaoshaUserKey.getById, "" + id, user);
        }
        return user;
    }

    public boolean updatePassword(String token, long id, String formPassword) {
        // 取user
        MiaoshaUser user = getById(id);
        if (user == null) {
            throw new GlobalExecption(CodeMsg.MOBILE_NOT_EXISTS);
        }
        // 更新数据库
        MiaoshaUser toBeUpdate = new MiaoshaUser();
        toBeUpdate.setId(id);
        toBeUpdate.setPassword(MD5Util.formPassToDBPass(formPassword, user.getSalt()));
        miaoshaUserDao.update(toBeUpdate);
        // 处理缓存
        redisService.delete(MiaoshaUserKey.getById, "" + id);
        user.setPassword(toBeUpdate.getPassword());
        redisService.set(MiaoshaUserKey.token, token, user);
        return true;
    }

    public String login(HttpServletResponse response, LoginVo loginVo) {
        if (loginVo == null) {
            throw new GlobalExecption(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        //判断手机号是否存在
        MiaoshaUser user = getById(Long.parseLong(mobile));
        if (user == null) {
            throw new GlobalExecption(CodeMsg.MOBILE_NOT_EXISTS);
        }
        //验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(password, saltDB);
        if (!calcPass.equals(dbPass)) {
            throw new GlobalExecption(CodeMsg.PASSWORD_ERROR);
        }
        //生成token写入cookies
        String token = UUIDUtil.uuid();
        addCookie(response, token, user);
        return token;

    }

    private void addCookie(HttpServletResponse response, String token, MiaoshaUser user) {

        redisService.set(MiaoshaUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKI_NAME_TOKEN, token);
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public MiaoshaUser getByToken(HttpServletResponse response, String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        MiaoshaUser miaoshaUser = redisService.get(MiaoshaUserKey.token, token, MiaoshaUser.class);
        //延长有效期
        if (miaoshaUser != null) {
            addCookie(response, token, miaoshaUser);
        }
        return miaoshaUser;
    }
}
