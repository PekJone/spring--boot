package com.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-17  15:06
 */
@Service
public class IdGenerator {
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    public void setRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    private static final String ID_KEY = "id:generator:cart";

    public Long incrementId(){
        long n = this.stringRedisTemplate.opsForValue().increment(ID_KEY);
        return n;
    }

}
