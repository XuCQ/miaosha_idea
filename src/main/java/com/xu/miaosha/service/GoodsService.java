package com.xu.miaosha.service;

import com.xu.miaosha.Vo.GoodsVo;
import com.xu.miaosha.dao.GoodsDao;
import com.xu.miaosha.domain.Goods;
import com.xu.miaosha.domain.MiaoshaGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: miaosha_idea
 * @description: 商品-Service
 * @author: Xu Changqing
 * @create: 2020-04-20 15:40
 **/
@Service
public class GoodsService {
    @Autowired
    GoodsDao goodsDao;

    public List<GoodsVo> listGoodsVo() {
        return goodsDao.listGoodsVo();
    }

    public GoodsVo getGoodVoByGoodsId(long goodsId) {
        return goodsDao.getGoodVoByGoodsId(goodsId);
    }

    public void reduceStock(GoodsVo goods) {
        MiaoshaGoods g = new MiaoshaGoods();
        g.setGoodsId(goods.getId());
        goodsDao.reduceStock(g);
    }
}
