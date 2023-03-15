package com.springboot.config;

import io.netty.util.concurrent.DefaultThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ChenKang
 * @date 2023/3/10 16:36
 */
@Configuration
public class ThreadPoolExecutorConfig {

    @Bean("threadPoolExecutor")
    ThreadPoolExecutor threadPoolExecutor(){
        return new ThreadPoolExecutor(
                2,
                10,
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(5),
                new DefaultThreadFactory("线程"),
                new ThreadPoolExecutor.CallerRunsPolicy());

    }


}
