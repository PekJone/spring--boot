package com.redis.task;

import com.redis.Util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.Map;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-18  16:06
 */
@Service
@Slf4j
public class OncCacheTask {
    private RedisTemplate redisTemplate;
    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    @PostConstruct
    public void initPV(){
        log.info("一级缓存，定时器。。。");
        new Thread(()->runCache()).start();
    }

    private void runCache() {
        while (true){
            this.consumePV();
            try {
                Thread.sleep(90000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            log.info("消费一级缓存 定时刷新");
        }

    }

    private void consumePV() {
       long m1 = System.currentTimeMillis()/(1000*60);
       Iterator<Long> iterable = Constants.PV_MAP.keySet().iterator();
       while (iterable.hasNext()){
           Long key = iterable.next();
           if(key<m1){
               Map<Integer, Integer> map = Constants.PV_MAP.get(key);
               this.redisTemplate.opsForList().leftPush(Constants.CACHE_PV_LIST,map);
               Constants.PV_MAP.remove(key);
               log.info("push进{}",map);
           }
       }
    }
}
