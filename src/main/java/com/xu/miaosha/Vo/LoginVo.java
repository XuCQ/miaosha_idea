package com.xu.miaosha.Vo;

import com.xu.miaosha.validator.isMoblie;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @program: miaosha_idea
 * @description:
 * @author: Xu Changqing
 * @create: 2020-04-12 00:09
 **/
public class LoginVo {
    @NotNull
    @isMoblie
    private String mobile;
    @NotNull
    @Length(min=32)
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginVo{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
