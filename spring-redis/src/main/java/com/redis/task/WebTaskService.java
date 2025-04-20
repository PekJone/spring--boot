package com.redis.task;

import com.redis.Util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-19  15:16
 */
@Service
@Slf4j
public class WebTaskService {
    private RedisTemplate redisTemplate;
    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void init(){
        log.info("启动初始化。。。。");
        //定时五秒钟，模拟微博热度刷新
        new Thread(()->this.refreshDataHour()).start();
        //定时一个小时合并统计 天 周 月 排行榜
        new Thread(()->this.refreshData()).start();

    }

    /**
     * 定时一个小时合并统计 天 周 月 排行榜
     */
    private void refreshData() {
        this.refreshDay();
        this.refreshWeek();
        this.refreshMonth();
        try{
            Thread.sleep(1000*60*60);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private void refreshMonth() {
        long hour = System.currentTimeMillis()/(1000*60*60);
        List<String> otherKeys = new ArrayList<>();
        for(int i=1;i<24*30-1;i++){
            String key = Constants.HOUR_KEY+(hour-1);
            otherKeys.add(key);
        }
        //把当前时间的key，并且后推23小时的key合并到Constants.DAY_KEY中
        this.redisTemplate.opsForZSet().unionAndStore(Constants.HOUR_KEY+hour,otherKeys,Constants.MONTH_KEY);
        log.info("月刷新完成");
    }

    private void refreshWeek() {
        long hour = System.currentTimeMillis()/(1000*60*60);
        List<String> otherKeys = new ArrayList<>();
        for(int i=1;i<24*7-1;i++){
            String key = Constants.HOUR_KEY+(hour-1);
            otherKeys.add(key);
        }
        //把当前时间的key，并且后推23小时的key合并到Constants.DAY_KEY中
        this.redisTemplate.opsForZSet().unionAndStore(Constants.HOUR_KEY+hour,otherKeys,Constants.WEEK_KEY);
        log.info("周刷新完成");
    }

    /**
     * 刷新当天的统计数据
     */
    private void refreshDay() {
        long hour = System.currentTimeMillis()/(1000*60*60);
        List<String> otherKeys = new ArrayList<>();
        for(int i=1;i<23;i++){
            String key = Constants.HOUR_KEY+(hour-1);
            otherKeys.add(key);
        }
        //把当前时间的key，并且后推23小时的key合并到Constants.DAY_KEY中
        this.redisTemplate.opsForZSet().unionAndStore(Constants.HOUR_KEY+hour,otherKeys,Constants.DAY_KEY);
        //设置当天的key  40天过期时间
        for(int i=0;i<24;i++){
            String key = Constants.HOUR_KEY+(hour-1);
            this.redisTemplate.expire(key,40, TimeUnit.DAYS);
        }
        log.info("天刷新完成");
    }

    /**
     * 模拟5秒刷新微博热度
     */
    private void refreshDataHour() {
        while (true){
            this.refreshHour();
            try{
                Thread.sleep(5000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private void refreshHour() {
        long hour = System.currentTimeMillis()/(1000*60*60);
        Random random = new Random();
        //采用24个字母来实现排行，随机为每个字母生成一个随机数作为score
        for (int i=1;i<=26;i++){
            this.redisTemplate.opsForZSet().incrementScore(Constants.HOUR_KEY+hour,String.valueOf((char)(96+i)),random.nextInt(10));
        }
    }
}
