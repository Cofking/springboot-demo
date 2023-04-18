package com.springboot;


import cn.hutool.core.thread.BlockPolicy;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class SpringbootApplicationTests {


    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    ThreadPoolExecutor threadPoolExecutor;

    @DisplayName("线程池测试")
    @Test
    void testThreadPool() {
        threadPoolExecutor.execute(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep(5000);
                System.out.println("线程");
            }
        });

        threadPoolExecutor.execute(() -> {
            try {
                Thread.sleep(5);
                System.out.println("线程");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
    }

    public static void main(String[] args) {
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
//                2,
//                10,
//                10,
//                TimeUnit.MINUTES,
//                new LinkedBlockingQueue<>(5),
//                new DefaultThreadFactory("线程"),
//                new BlockPolicy());
//        for (int i = 0; i < 10; i++) {
//            threadPoolExecutor.execute(() -> {
//                try {
//                    Thread.sleep(1000);
//                    System.out.println("任务总数"+ threadPoolExecutor.getTaskCount());
//                    System.out.println("已完成任务" + threadPoolExecutor.getCompletedTaskCount());
//                    System.out.println("正在执行任务线程数量" + threadPoolExecutor.getActiveCount());
//                    System.out.println("队列任务数"+threadPoolExecutor.getQueue().size());
//                    System.out.println("核心线程数量" + threadPoolExecutor.getCorePoolSize());
//                    System.out.println("当前线程数量" + threadPoolExecutor.getPoolSize());
//                    System.out.println("同时进行的最大线程数"+threadPoolExecutor.getLargestPoolSize());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            });
//        }
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

        threadPoolTaskExecutor.initialize();
        threadPoolTaskExecutor.execute(() -> {
            try {
                    Thread.sleep(1000);

                    System.out.println("正在执行任务线程数量" + threadPoolTaskExecutor.getActiveCount());
                    System.out.println("核心线程数量" + threadPoolTaskExecutor.getCorePoolSize());
                    System.out.println("当前线程数量" + threadPoolTaskExecutor.getPoolSize());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        });


    }

}
