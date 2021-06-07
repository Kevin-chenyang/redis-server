package com.myproject.redisserver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @program redis-server
 * @description: 唯一订单号生成器
 * @author: chenyang
 * @create: 2021/05/27 08:35
 */
public class CreateOrderCode {


    /**
     * 时间戳+随机数+redis自增序列+用户ID
     * @param userId
     * @return
     */
    private String getOrderCode(String userId){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String timeStr = LocalDateTime.now().format(format);
        int num = (int) (Math.random() * 900 + 100);
        String code = timeStr + num + userId;
        //利用redis的自增功能获取最新的序列号
        return code;
    }
}
