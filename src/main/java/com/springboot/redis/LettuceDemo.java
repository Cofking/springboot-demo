package com.springboot.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

/**
 * springboot  redis -start 默认 letturce ，jedis需要额外导入
 *
 * @author ChenKang
 * @date 2023/4/14 10:57
 */
public class LettuceDemo {
    public static void main(String[] args) {
        //使用构建器 RedisURI.builder
        RedisURI uri = RedisURI.Builder.redis("192.168.2.148").withPort(6378).withAuthentication("default","111111").build();
        //创建连接客户端
        RedisClient client = RedisClient.create(uri);
        StatefulRedisConnection conn = client.connect();
        //操作命令api
        RedisCommands<String,String> commands = conn.sync();
        System.out.println(commands.ping());

        // 关闭流  try catch  finally
        conn.close();
        client.shutdown();
    }
}
