package com.myproject.redisserver.utils;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @program redis-server
 * @description:
 * @author: chenyang
 * @create: 2021/05/27 11:01
 */
@Slf4j
@Component
public class RedissionLockUtil {
    @Autowired
    private RedissonClient redissonClient;

    /**
     * 加锁
     * @param lockname 名称
     */
    public Boolean lock(String lockname){
        try {
            if(null == redissonClient){
                log.error("RedissonClient is null");
                return false;
            }
            RLock lock = redissonClient.getLock(lockname);
            //60s内没有获取到锁则加锁失败，lockWatchdogTimeout作为锁的失效时间，开启看门狗，会定时延长锁的失效时间
            boolean result = lock.tryLock(60, TimeUnit.SECONDS);
            return result;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 释放锁
     * @param lockname
     */
    public Boolean unlock(String lockname){
        try {
            if(null == redissonClient){
                log.error("RedissonClient is null");
                return false;
            }
            RLock lock = redissonClient.getLock(lockname);
            lock.unlock();
            log.info("===》》》》释放锁成功");
            return true;
        } catch (Exception e) {
            log.info(e.getMessage(),e);
            return false;
        }
    }
}
