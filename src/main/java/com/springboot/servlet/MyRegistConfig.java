package com.springboot.servlet;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//该方式适合引入第三方库时使用，推荐直接使用注解方式调用
@Configuration(proxyBeanMethods = true)  //保证依赖的组件始终是单实例的  默认 true  单例
public class MyRegistConfig {

    @Bean
    public ServletRegistrationBean<MyServlet> myServlet(){
        MyServlet myServlet = new MyServlet();
        return  new ServletRegistrationBean<>(myServlet,"/my","/my02");
    }

    @Bean
    public FilterRegistrationBean<MyFilter> myFilter(){
        MyFilter myFilter=new MyFilter();

//        return new FilterRegistrationBean<>(myFilter,myServlet());  //传递一个 servlet 过滤该servlet
        FilterRegistrationBean<MyFilter> filterRegistrationBean = new FilterRegistrationBean<>(myFilter);
        filterRegistrationBean.addUrlPatterns("/my","/my02");  //自定义过滤路径
        return filterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean<MyListenner> myListener(){
        MyListenner myListenner = new MyListenner();
        return new ServletListenerRegistrationBean<>(myListenner);
    }

}
