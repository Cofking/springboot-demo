package com.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

//@ServletComponentScan("com.springboot")
@SpringBootApplication
@EnableScheduling  // 开启定时任务
@EnableAsync //开启异步线程池
@MapperScan("com.springboot.mapper")//mapper文件扫描
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
        System.out.println("启动成功");
    }

}
