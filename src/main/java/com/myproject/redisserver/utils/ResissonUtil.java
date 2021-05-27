package com.myproject.redisserver.utils;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @program redis-server
 * @description:
 * @author: chenyang
 * @create: 2021/05/27 10:58
 */
@Slf4j
@Component
public class ResissonUtil {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 获取RedissonClient 配置信息
     */
    public void getRedissonConfig() throws IOException {
        Config config = redissonClient.getConfig();
//        System.out.println(config.toJSON());
    }
}
