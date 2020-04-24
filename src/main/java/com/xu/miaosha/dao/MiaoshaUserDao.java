package com.xu.miaosha.dao;

import com.xu.miaosha.domain.MiaoshaUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @program: miaosha_idea
 * @description: 用户dao
 * @author: Xu Changqing
 * @create: 2020-04-12 02:10
 **/
@Mapper
public interface MiaoshaUserDao {
    @Select("select * from miaosha_user where id=#{id}")
    public MiaoshaUser getById(@Param("id") long id);

    @Update("update miaosha_user set password=#{password} where id=#{id}")
    public void update(MiaoshaUser toBeUpdate);
}
