package com.redis.controller;

import com.redis.bean.Cart;
import com.redis.bean.CookieCart;
import com.redis.service.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-17  11:08
 */
@RestController
@RequestMapping("/cookieCart")
public class CookieCartController {
    private RedisTemplate redisTemplate;
    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private HttpServletRequest request;

    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    private HttpServletResponse response;

    @Autowired
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    private IdGenerator idGenerator;
    @Autowired
    public void setIdGenerator(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }
    public static final String COOKIE_KEY = "cart:cookie:";
    @PostMapping(value = "/addCart")
    public void addCart(CookieCart obj){
        String cartId = this.getCookiesCartId();
        String key = COOKIE_KEY+cartId;
        Boolean hasKey = redisTemplate.opsForHash().getOperations().hasKey(key);
        if(hasKey){
            this.redisTemplate.opsForHash().put(key,obj.getProductId()+"",obj.getAmount());
        }else {
            this.redisTemplate.opsForHash().put(key,obj.getProductId()+"",obj.getAmount());
            this.redisTemplate.expire(key,90, TimeUnit.DAYS);
        }
    }

    /**
     * 修改购物车数量
     */
    @PostMapping("/updateCart")
    public void updateCart(Cart obj){
        String cartId = this.getCookiesCartId();
        String key = COOKIE_KEY+cartId;
        this.redisTemplate.opsForHash().put(key,obj.getProductId()+"",obj.getAmount());
    }

    /**
     * 合并购物车
     * @return
     */
    @PostMapping("/merge")
    public void mergeCart(int userId){
        String cartId = this.getCookiesCartId();
        String keyCookie = COOKIE_KEY+cartId;
        Map<String,Integer> map = this.redisTemplate.opsForHash().entries(keyCookie);

        String keyUser = "cart:user:"+userId;
        this.redisTemplate.opsForHash().putAll(keyUser,map);
        this.redisTemplate.opsForHash().delete(keyCookie);
        Cookie cookies = new Cookie("cardId",null);
        cookies.setMaxAge(0);
        response.addCookie(cookies);
    }


    public String getCookiesCartId(){
        javax.servlet.http.Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for (javax.servlet.http.Cookie cookie:cookies){
                if(cookie.getName().equals("cardId")){
                    return cookie.getValue();
                }
            }
        }
        long id = this.idGenerator.incrementId();
        javax.servlet.http.Cookie cookie = new Cookie("cardId",String.valueOf(id));
        response.addCookie(cookie);
        return id+"";
    }
}
