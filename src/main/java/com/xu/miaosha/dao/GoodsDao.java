package com.xu.miaosha.dao;

import com.xu.miaosha.Vo.GoodsVo;
import com.xu.miaosha.domain.Goods;
import com.xu.miaosha.domain.MiaoshaGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @program: miaosha_idea
 * @description:商品列表查询
 * @author: Xu Changqing
 * @create: 2020-04-20 15:52
 **/
@Mapper
public interface GoodsDao {
    @Select("SELECT g.*,mg.`stock_count`,mg.start_date,mg.end_date,mg.miaosha_price FROM miaosha_goods mg LEFT JOIN goods g ON mg.goods_id=g.id")
    public List<GoodsVo> listGoodsVo();

    @Select("SELECT g.*,mg.`stock_count`,mg.start_date,mg.end_date,mg.miaosha_price FROM miaosha_goods mg LEFT JOIN goods g ON mg.goods_id=g.id where g.id=#{goodsId}")
    GoodsVo getGoodVoByGoodsId(@Param("goodsId") long goodsId);

    @Update("UPDATE miaosha_goods SET stock_count=stock_count-1 WHERE goods_id=#{goodsId} and stock_count>0")   // stock_count>0 防止库存产生负数
    public int reduceStock(MiaoshaGoods g);

    @Update("update miaosha_goods set stock_count = #{stockCount} where goods_id = #{goodsId}")
    public int resetStock(MiaoshaGoods g);
}
