package com.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author ChenKang
 * @date 2023/3/10 16:37
 */
@Configuration
public class ThreadPoolTaskExecutorConfig {

    @Bean("threadPoolTaskExecutor")
     ThreadPoolTaskExecutor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        //核心线程数
        threadPoolTaskExecutor.setCorePoolSize(1);
        //最大线程数
        threadPoolTaskExecutor.setMaxPoolSize(5);
        //线程队列数
        threadPoolTaskExecutor.setQueueCapacity(10);
        //存活时间
        threadPoolTaskExecutor.setKeepAliveSeconds(60);
        //线程前缀名
        threadPoolTaskExecutor.setThreadNamePrefix("线程");
        // 停机策略 | 该方法用来设置 线程池关闭 的时候 等待 所有任务都完成后，再继续 销毁 其他的 Bean，
        // 这样这些 异步任务 的 销毁 就会先于 数据库连接池对象 的销毁。
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        // 任务的等待时间 如果超过这个时间还没有销毁就 强制销毁，以确保应用最后能够被关闭，而不是阻塞住。
        threadPoolTaskExecutor.setAwaitTerminationSeconds(60);
        // 拒绝策略
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return  threadPoolTaskExecutor;
    }

}
