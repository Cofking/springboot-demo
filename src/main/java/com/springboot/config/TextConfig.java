package com.springboot.config;

import com.springboot.constant.Constants;
import com.springboot.converter.DIYMessageConverter;
import com.springboot.interceptor.TestInterceptorA;
import com.springboot.interceptor.TestInterceptorB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


@Configuration
public class TextConfig {

    @Autowired
    TestInterceptorA testInterceptorA;

    @Autowired
    TestInterceptorB testInterceptorB;

    @Autowired
    ChenKangConfig chenKangConfig;

    /**
     * 自定义表单rest风格请求 时候 附带的自定义隐藏参数--该参数数值是请求的方式字符串 然后请求被HiddenHttpMethodFilter拦截
     *
     * @return
     */
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {

        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        hiddenHttpMethodFilter.setMethodParam("_m");
        return hiddenHttpMethodFilter;
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {

//        return new WebMvcConfigurer() {
//            //自定义转换器  前端传过来的数据 根据自定义转换器 绑定数据
//            @Override
//            public void addFormatters(FormatterRegistry registry) {
//                registry.addConverter();
//            }
//        };

        return new WebMvcConfigurer() {
            // 资源映射
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                /** 本地文件上传路径 */
                registry.addResourceHandler(Constants.RESOURCE_PREFIX + "/**")
                        .addResourceLocations("file:" + chenKangConfig.getProfile() + "/");
            }

            //自定义消息响应处理器
            @Override
            public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
                converters.add(new DIYMessageConverter());
            }

//            @Override
//            public void addInterceptors(InterceptorRegistry registry) {
//                registry.addInterceptor(testInterceptorA).addPathPatterns("/**") //拦截所有请求
//                        .excludePathPatterns("/img/**");//放行
//                registry.addInterceptor(testInterceptorB).addPathPatterns("/**") //拦截所有请求
//                        .excludePathPatterns("/img/**");//放行
//            }
        };

    }


}
