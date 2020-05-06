package com.xu.miaosha.config;

import com.xu.miaosha.access.AccessInterceptor;
import com.xu.miaosha.result.CodeMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @program: miaosha_idea
 * @description: web配置
 * @author: Xu Changqing
 * @create: 2020-04-12 22:35
 **/

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    UserArguementResolver userArguementResolver;
    @Autowired
    AccessInterceptor accessInterceptor;


    /**
     * 框架回调方法，往controller中赋值
     *
     * @param resolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userArguementResolver);
    }

    /**
     * 注册拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessInterceptor);
    }

}
