package com.xu.miaosha.redis;

/**
 * @program: miaosha_idea
 * @description:
 * @author: Xu Changqing
 * @create: 2020-04-09 11:43
 **/
public abstract class BasePrefix implements KeyPrefix {
    private int expireSeconds;
    private String prefix;

    public BasePrefix(String prefix) {
        this(0,prefix);
    }

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    /**
     * 默认0永不过期
     */
    @Override
    public int expireSeconds() {
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        //通过类名保证前缀不重复
        String className = getClass().getSimpleName();
        return className + "." + prefix;
    }
}
