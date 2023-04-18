package com.springboot.service;

/**
 * @author ChenKang
 * @date 2023/4/14 17:18
 */
public interface RedisService {
    void add(String key, String value);

    String select(String key);
}
