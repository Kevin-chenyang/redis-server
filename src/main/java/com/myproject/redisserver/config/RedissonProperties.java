package com.myproject.redisserver.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program redis-server
 * @description:
 * @author: chenyang
 * @create: 2021/05/27 10:20
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedissonProperties {

    private String host;
    private int port;
    private String password;
    private int database;
    private String timeout;
    private int cachetime;
    private int lockWatchdogTimeout;

    public static class Pool{
        private int maxActive;
        private int maxWait;
        private int maxIdle;
        private int minIdle;
    }
}

