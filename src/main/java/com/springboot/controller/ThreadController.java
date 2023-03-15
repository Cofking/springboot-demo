package com.springboot.controller;

import cn.hutool.json.JSONObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author ChenKang
 * @date 2023/3/13 9:07
 */
@Slf4j
@Controller
public class ThreadController {

    @Autowired
    ThreadPoolExecutor threadPoolExecutor;

    @RequestMapping("/showPage")
    public String showPage() {
        return "threadPage";
    }


    @RequestMapping("/showThread")
    @ResponseBody
    public String showThread() {
        JSONObject json = new JSONObject();
        HashMap<String, String> map = new HashMap<>(10);

        map.put("任务总数", threadPoolExecutor.getTaskCount() + "");
        map.put("已完成任务", threadPoolExecutor.getCompletedTaskCount() + "");
        map.put("正在执行任务线程数量", threadPoolExecutor.getActiveCount() + "");
        map.put("队列任务数", threadPoolExecutor.getQueue().size() + "");
//            map.put("核心线程数量",threadPoolExecutor.getCorePoolSize() +"");
        map.put("当前线程数量", threadPoolExecutor.getPoolSize() + "");
//            map.put("同时进行的最大线程数",threadPoolExecutor.getLargestPoolSize() +"");
        json.set("data", map);
        return json.toString();
    }

    @RequestMapping("/doThread")
    @ResponseBody
    public String doThread() {
        JSONObject json = new JSONObject();

        threadPoolExecutor.execute(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep(10000);
                System.out.println(Thread.currentThread().getName());
                System.out.println("任务结束");
            }
        });
        json.set("message", "执行任务....");
        return json.toString();
    }

    @ResponseBody
    @RequestMapping("/threadTest")
//    @Async("threadPoolExecutor")
    public void testThreadPool() {
        log.info("用户请求：start submit");
        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    Thread thread = Thread.currentThread();
                    log.info("开始执行任务："+thread.getName()+"|"+thread.getId());
                    Thread.sleep(10000);
                    log.info("任务结束："+thread.getName()+"|"+thread.getId());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });

        }
        log.info("请求结束：end submit");
    }

}
