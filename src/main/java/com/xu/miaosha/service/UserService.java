package com.xu.miaosha.service;

import com.xu.miaosha.dao.UserDao;
import com.xu.miaosha.domain.User;
import com.xu.miaosha.result.CodeMsg;
import com.xu.miaosha.Vo.LoginVo;
import com.xu.miaosha.dao.MiaoshaUserDao;
import com.xu.miaosha.domain.MiaoshaUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: miaosha_idea
 * @description: 获得User的查询结构并返回
 * @author: Xu Changqing
 * @create: 2020-04-08 20:43
 **/
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User getById(int id) {
        return userDao.getById(id);
    }

    /**
     * 事务测试 Transactional
     *
     * @return T/F
     */
    @Transactional
    public boolean tx() {
        User user1 = new User(3, "333");
        userDao.insert(user1);
        User user2 = new User(4, "4444");
        userDao.insert(user2);
        return true;
    }


}
