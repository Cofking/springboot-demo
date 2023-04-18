package com.springboot.service.impl;

import com.springboot.service.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ChenKang
 * @date 2023/4/14 17:18
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Resource
    RedisTemplate redisTemplate;

    @Override
    public void add(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public String select(String key) {
        return redisTemplate.opsForValue().get(key).toString();
    }
}
