package com.xu.miaosha.dao;

import com.xu.miaosha.domain.MiaoshaOrder;
import com.xu.miaosha.domain.OrderInfo;
import org.apache.ibatis.annotations.*;

/**
 * @program: miaosha_idea
 * @description: 订单查询
 * @author: Xu Changqing
 * @create: 2020-04-20 22:28
 **/
@Mapper
public interface OrderDao {
    @Select("SELECT * FROM miaosha_order WHERE user_id =#{userId} and goods_id=#{goodsId}")
    MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(@Param("userId") long userId, @Param("goodsId") long goodsId);

    @Insert("INSERT INTO order_info(user_id,goods_id,delivery_addr_id,goods_name,goods_count,goods_price,order_channel,status,create_date)values(#{userId},#{goodsId},#{deliveryAddrId},#{goodsName},#{goodsCount},#{goodsPrice},#{orderChannel},#{status},#{createDate})")
    // 用于替换xml中的<selectKey/>标签，用于返回新插入数据的id值。
    @SelectKey(keyColumn = "id", keyProperty = "id", before = false, resultType = Long.class, statement = "select last_insert_id()")
    long insert(OrderInfo orderInfo);

    @Insert("insert into miaosha_order(user_id,order_id,goods_id) values(#{userId},#{orderId},#{goodsId}) ")
    void insertMiaoshaOrder(MiaoshaOrder miaoshaOrder);

    @Select("select * from order_info where id=#{orderId}")
    OrderInfo getOrderById(@Param("orderId") long orderId);

    @Delete("delete from order_info")
    public void deleteOrders();

    @Delete("delete from miaosha_order")
    public void deleteMiaoshaOrders();
}
