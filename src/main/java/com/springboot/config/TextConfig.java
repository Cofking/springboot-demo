package com.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;


@Configuration
public class TextConfig {

    /**
     * 自定义表单rest风格请求 时候 附带的自定义隐藏参数--该参数数值是请求的方式字符串 然后请求被HiddenHttpMethodFilter拦截
      * @return
     */
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter (){

        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        hiddenHttpMethodFilter.setMethodParam("_m");
        return hiddenHttpMethodFilter;
    }

//    @Bean
//    public WebMvcConfigurer webMvcConfigurer(){
//
//        return new WebMvcConfigurer() {
//            //自定义转换器  前端传过来的数据 根据自定义转换器 绑定数据
//            @Override
//            public void addFormatters(FormatterRegistry registry) {
//                registry.addConverter();
//            }
//        };
//    }


}
