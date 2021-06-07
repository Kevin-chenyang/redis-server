package com.myproject.redisserver;

import com.myproject.redisserver.utils.RedissionLockUtil;
import com.myproject.redisserver.utils.ResissonUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @program redis-server
 * @description:
 * @author: chenyang
 * @create: 2021/05/27 09:33
 */
@RestController
@RequestMapping("/redis")
@Slf4j
public class ReddissonController {
    @Autowired
    private RedissionLockUtil lockUtil;

    @Autowired
    private RedissonClient redissonClient;

    @PostMapping("/test")
    public void testLock(){
        Boolean myredislock = lockUtil.lock("myredislock");
        if(!myredislock){
            log.info("》》》》获取锁失败《《《《");
            return;
        }
        try {
//            resissonUtil.getRedissonConfig();
            log.info("加锁成功");
            Thread.sleep(40000);
            log.info("============================");
            log.info("程序第一次休眠40s结束");
            log.info("============================");
            Thread.sleep(10000);
            log.info("程序第二次休眠10s结束");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lockUtil.unlock("myredislock");
        }
    }

    public void test(String lockname) throws InterruptedException {
        //判断是否已经买过该商品了，买过直接返回
        RLock lock = redissonClient.getLock(lockname);
        boolean result = lock.tryLock(5, TimeUnit.SECONDS);
        //加锁
        if(lock.tryLock(5, TimeUnit.SECONDS)){

        }
    }

}
