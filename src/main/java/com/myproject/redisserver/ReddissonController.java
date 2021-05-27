package com.myproject.redisserver;

import com.myproject.redisserver.utils.RedissionLockUtil;
import com.myproject.redisserver.utils.ResissonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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
    private ResissonUtil resissonUtil;
    @Autowired
    private RedissionLockUtil lockUtil;

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

}
