package com.xu.miaosha.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @program: miaosha_idea
 * @description: 提供Redis服务
 * @author: Xu Changqing
 * @create: 2020-04-09 02:40
 **/
@Service
public class RedisService {
    @Autowired
    JedisPool jedisPool;

    /**
     * 获取单个对象
     *
     * @param prefix 前缀
     * @param key    目标key
     * @param clazz  目标类型
     * @param <T>    类型
     * @return T类型对象
     */
    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = prefix.getPrefix() + key;
            String str = jedis.get(realKey);
            T t = stringToBean(str, clazz);
            return t;
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 设置对象
     *
     * @param prefix 前准
     * @param key    目标key
     * @param value  目标value
     * @param <T>    类型
     * @return T/F
     */
    public <T> boolean set(KeyPrefix prefix, String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if (str == null || str.length() < 0) {
                return false;
            }
            String realKey = prefix.getPrefix() + key;
            int seconds = prefix.expireSeconds();
            if (seconds <= 0) {
                jedis.set(realKey, str);
            } else {
                jedis.setex(realKey, seconds, str);
            }
            return true;
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 判断key是否存在
     *
     * @param prefix 前缀
     * @param key    目标key
     * @param <T>    类型
     * @return T/F
     */
    public <T> boolean exists(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.exists(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 增加1，redis.incr
     * @param prefix 前缀
     * @param key 自增key
     * @param <T> 类型
     * @return 自增后的值
     */

    public <T> Long incr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.incr(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 减少1，redis.incr
     * @param prefix 前缀
     * @param key 自减key
     * @param <T> 类型
     * @return 自减后的值
     */
    public <T> Long decr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.decr(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * bean转String
     * @param value 对象
     * @param <T> 类型
     * @return 字符串
     */
    private <T> String beanToString(T value) {
        if (value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return "" + value;
        } else if (clazz == String.class) {
            return (String) value;
        } else if (clazz == Long.class) {
            return "" + value;

        } else {
            return JSON.toJSONString(value);
        }
    }

    /**
     * 字符串转bean
     * @param str redis返回值 {}类型
     * @param clazz bean 原始类
     * @param <T> 类型
     * @return T对象
     */
    private <T> T stringToBean(String str, Class<T> clazz) {
        if (str == null || str.length() < 0) {
            return null;
        }
        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if (clazz == String.class) {
            return (T) str;
        } else if (clazz == Long.class) {
            return (T) Long.valueOf(str);

        } else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

    private void returnToPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }


}
