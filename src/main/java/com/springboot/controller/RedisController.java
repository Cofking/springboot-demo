package com.springboot.controller;

import com.springboot.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ChenKang
 * @date 2023/4/14 16:44
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    RedisService redisService;

    @RequestMapping("/save")
    public void add(String key, String value) {
        redisService.add(key, value);
    }

    @RequestMapping("/get")
    public String get(String key) {
        long l1 = System.currentTimeMillis();
        String s = redisService.select(key);
        long l2 = System.currentTimeMillis();
        System.out.println("耗时："+(l2-l1));
        return s;
    }
}
