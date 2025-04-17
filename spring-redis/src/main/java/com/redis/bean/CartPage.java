package com.redis.bean;

import lombok.Data;

import java.util.List;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-17  10:31
 */
@Data
public class CartPage<T> {
    private List<T> cartList;
    private int count ;
}
