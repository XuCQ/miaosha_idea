package com.xu.miaosha.redis;

/**
 * key 前缀
 *
 * @author lenovo
 */
public interface KeyPrefix {

    /**
     * 过期时间
     *
     * @return 秒
     */
    public int expireSeconds();


    /**
     * 获得前缀
     *
     * @return String
     */
    public String getPrefix();
}
