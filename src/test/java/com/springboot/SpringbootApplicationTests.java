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
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                10,
                10,
                TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(5),
                new DefaultThreadFactory("线程"),
                new BlockPolicy());
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("队列" + threadPoolExecutor.getQueue());
                    System.out.println("已完成任务" + threadPoolExecutor.getCompletedTaskCount());
                    System.out.println("存活数量" + threadPoolExecutor.getActiveCount());
                    System.out.println("核心线程数量" + threadPoolExecutor.getCorePoolSize());
                    System.out.println("当前线程数量" + threadPoolExecutor.getPoolSize());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }


    }

}
