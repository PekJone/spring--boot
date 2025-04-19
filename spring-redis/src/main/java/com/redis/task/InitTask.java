package com.redis.task;

import com.redis.Util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-18  15:17
 */
@Service
@Slf4j
public class InitTask {
    private RedisTemplate redisTemplate;
    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    @PostConstruct
   public void initPV(){
        log.info("启动模拟大量PV请求，定时器。。。");
        new Thread(()->runActivityPV()).start();
   }

    private void runActivityPV() {
        while (true){
            this.batchAddArticle();
            try {
                Thread.sleep(5000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private void batchAddArticle() {
        for (int i=0;i<1000;i++){
            this.addPV(Integer.valueOf(i));
        }
    }

    private void addPV(Integer id) {
       long m1 = System.currentTimeMillis()/(1000*60*5);
       Map<Integer,Integer> map = Constants.PV_MAP.get(m1);
       if(CollectionUtils.isEmpty(map)){
           map = new ConcurrentHashMap<>();
           map.put(id,Integer.valueOf(1));
           Constants.PV_MAP.put(m1,map);
       }else {
           Integer value = map.get(id);
           if(null==value){
               map.put(id,Integer.valueOf(1));
           }else {
               map.put(id,value+1);
           }
       }

    }


}
