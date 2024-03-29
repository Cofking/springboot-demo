package com.springboot.config;


import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * 监听项目启动之后打开网页
 * @author ChenKang
 */
@Configuration
public class AutoBrower {
//    @EventListener({ApplicationReadyEvent.class})
    public void applicationReadyEvent() {
        System.out.println("应用已经准备就绪 ... 启动浏览器");
        // 这里需要注url:端口号+测试类方法名
        String url = "http://localhost:8888/";
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
