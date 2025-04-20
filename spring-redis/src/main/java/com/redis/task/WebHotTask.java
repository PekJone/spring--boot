package com.redis.task;

import com.redis.Util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-19  11:24
 */
@Service
@Slf4j
public class WebHotTask {
     private RedisTemplate redisTemplate;
     @Autowired
     public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
     }

    /**
     * 初始化一个月的历史数据
     */
    public void initThirtyDays(){
        //计算当前的小时Key
         long hour = System.currentTimeMillis()/(1000*60*60);
         //初始化近30天 每天24个key
         for (int i=0;i<24*30;i++){
             //倒推过去30天
             String key = Constants.HOUR_KEY+(hour-i);
             this.initMember(key);
             System.out.println(key);
         }
     }

    /**
     * 初始化某个小时的key
     * @param key
     */
    private void initMember(String key) {
        Random random = new Random();
        //采用24个字母来实现排行，随机为每个字母生成一个随机数作为score
        for (int i=1;i<=26;i++){
            this.redisTemplate.opsForZSet().add(key,String.valueOf((char)(96+i)),random.nextInt(10));
        }
    }
}
