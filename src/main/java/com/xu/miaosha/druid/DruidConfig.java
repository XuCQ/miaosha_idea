package com.xu.miaosha.druid;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: miaosha_idea
 * @description: 配置druid监控 和 拦截器
 * @author: Xu Changqing
 * @create: 2020-04-08 23:04
 **/
@Configuration
public class DruidConfig {

    /**
     * 配置druid监控
     * 配置一个管理后台的servlet
     * 访问地址：http://localhost:8080/druid/
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> initParameters = new HashMap<String, String>();
        //属性见：com.alibaba.druid.support.http.ResourceServlet
        initParameters.put("loginUsername", "admin");
        initParameters.put("loginPassword", "123456");
        //默认允许所有
        initParameters.put("allow", "");
        initParameters.put("deny", "");
        bean.setInitParameters(initParameters);
        return bean;
    }

    /**
     * 配置一个web监控的filter
     *
     * @return filterBean
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean filterBean = new FilterRegistrationBean();
        filterBean.setFilter(new WebStatFilter());
        filterBean.setUrlPatterns(Arrays.asList("/*"));

        Map<String, String> initParameters = new HashMap<String, String>();
        //属性见：com.alibaba.druid.support.http.WebStatFilter
        initParameters.put("exclusions", "*.js,*.css,/druid/*");
        filterBean.setInitParameters(initParameters);

        return filterBean;
    }

}
