package com.redis.task;

import com.redis.Util.Constants;
import com.redis.bean.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-18  8:59
 */
@Service
@Slf4j
public class TaskService {
    private RedisTemplate redisTemplate;
    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    @PostConstruct
    public void initJHS(){
        log.info("启动定时器。。。。");
        new Thread(()->runJHS()).start();
    }

    /**
     *模拟定时器，定时把数据库的商品刷到redis中
     */
    private void runJHS() {
        while (true){
            //模拟数据库读取数据
            List<Product> list = this.products();
            this.redisTemplate.delete(Constants.JHS_KEY);
            this.redisTemplate.opsForList().leftPushAll(Constants.JHS_KEY,list);
            try {
                  Thread.sleep(1000*60);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            log.info("JHS定时刷新");
        }

    }

    /**
     * 模拟从数据库读取100件特价商品  用于加载到聚划算的页面
     * @return
     */
    private List<Product> products() {
         List<Product> list = new ArrayList<>();
         for(int i=0;i<100;i++){
             Random random =new Random();
             int id = random.nextInt(10000);
             Product product = new Product(id,"product"+i,i,"detail");
             list.add(product);
         }
         return list;
    }
}
