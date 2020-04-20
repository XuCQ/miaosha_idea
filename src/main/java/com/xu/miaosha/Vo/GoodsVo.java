package com.xu.miaosha.Vo;

import com.xu.miaosha.domain.Goods;

import java.util.Date;

/**
 * @program: miaosha_idea
 * @description: 将秒杀商品和商品表对象连接
 * @author: Xu Changqing
 * @create: 2020-04-20 15:57
 **/
public class GoodsVo extends Goods {
    private Integer stockCount;
    private Date startDate;
    private Date endDate;

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return super.toString() + "GoodsVo{" +
                "stockCount=" + stockCount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
