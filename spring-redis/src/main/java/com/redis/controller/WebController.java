package com.redis.controller;

import com.redis.Util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-19  16:00
 */
@RestController
@Slf4j
public class WebController {
    private RedisTemplate redisTemplate ;

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/getHour")
    public Set getHour(){
        long hour = System.currentTimeMillis()/(1000*60*60);
        Set<ZSetOperations.TypedTuple<Integer>> rang = this.redisTemplate.opsForZSet().reverseRangeWithScores(Constants.HOUR_KEY+hour,0,30);
        return rang;
    }


    @GetMapping("/getDay")
    public Set getDay(){
        Set<ZSetOperations.TypedTuple<Integer>> rang = this.redisTemplate.opsForZSet().reverseRangeWithScores(Constants.DAY_KEY,0,30);
        return rang;
    }

    @GetMapping("/getWeek")
    public Set getWeek(){
        Set<ZSetOperations.TypedTuple<Integer>> rang = this.redisTemplate.opsForZSet().reverseRangeWithScores(Constants.WEEK_KEY,0,30);
        return rang;
    }

    @GetMapping("/getMonth")
    public Set getMonth(){
        Set<ZSetOperations.TypedTuple<Integer>> rang = this.redisTemplate.opsForZSet().reverseRangeWithScores(Constants.MONTH_KEY,0,30);
        return rang;
    }

}
