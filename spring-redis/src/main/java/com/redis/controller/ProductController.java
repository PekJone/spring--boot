package com.redis.controller;

import com.redis.bean.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-15  17:30
 */
@Slf4j
@RestController
@RequestMapping("product")
public class ProductController {

    private RedisTemplate redisTemplate;
    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    @PostMapping(value = "/create")
    public void createProduct(Product obj){
        String key = "product"+1000;
        //将Object对象里面的属性转换为Map对象
        Map<String,Object> map = this.objectToMap(obj);
        this.redisTemplate.opsForHash().putAll(key,map);

        Object name  = this.redisTemplate.opsForHash().get(key,"name");
        log.info("name:{}",name);
        Object price = this.redisTemplate.opsForHash().get(key,"price");
        log.info("price:{}",price);
        Object detail = this.redisTemplate.opsForHash().get(key,"detail");
        log.info("detail:{}",detail);

    }

    @PostMapping(value = "/addPrice")
    public void addPrice(int id,int price){
        String key = "product"+id;
        this.redisTemplate.opsForHash().increment(key,"price",price);
        Object price2 = this.redisTemplate.opsForHash().get(key,"price");
        log.info("price={}",price2);
    }

    /**
     * 将object对象里面的属性和值转换为Map对象
     * @param obj
     * @return
     */
    private Map<String, Object> objectToMap(Product obj) {
        Map<String,Object> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        for (Field field:clazz.getDeclaredFields()){
            field.setAccessible(true);
            String fileName = field.getName();
            Object value= null;
            try {
                value = field.get(obj);
            }catch (IllegalAccessException e){
                log.error(e.getMessage());
            }
            map.put(fileName,value);
        }
        return map;
    }
}
