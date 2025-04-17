package com.redis.controller;

import com.mysql.cj.util.TimeUtil;
import com.redis.bean.Cart;
import com.redis.bean.CartPage;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-17  10:08
 */
@RestController
@RequestMapping("/userCart")
public class UserCartController {
    private RedisTemplate redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public static final String CART_KEY = "cart:user";

    /**
     * 添加购物车
     * @param obj
     */
    @PostMapping("/addCart")
    public void addCart(Cart obj){
       String key = CART_KEY+obj.getUserId();
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
        String key = CART_KEY+obj.getUserId();
        this.redisTemplate.opsForHash().put(key,obj.getProductId()+"",obj.getAmount());
    }


    @PostMapping("/deteleCart")
    public void deteleCart(int userId,int productId){
        String key = CART_KEY +userId;
        this.redisTemplate.opsForHash().delete(key,productId+"");
    }
    @PostMapping("/findAll")
    public CartPage findAll(int userId){
        String key = CART_KEY+userId;
        CartPage cartPage = new CartPage();
        long size = redisTemplate.opsForHash().size(key);
        cartPage.setCount((int)size);
        Map<String,Integer> map = this.redisTemplate.opsForHash().entries(key);
        List<Cart> cartList = new ArrayList<>();
        for (Map.Entry<String,Integer> entry:map.entrySet()){
            Cart cart = new Cart();
            cart.setUserId(userId);
            cart.setProductId(Integer.valueOf(entry.getKey()));
            cart.setAmount(entry.getValue());
            cartList.add(cart);
        }
        cartPage.setCartList(cartList);
        return cartPage;
    }
}
