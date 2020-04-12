package com.xu.miaosha.domain;

/**
 * @program: miaosha_idea
 * @description: User 表对象的类
 * @author: Xu Changqing
 * @create: 2020-04-08 20:23
 **/
public class User {

    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
