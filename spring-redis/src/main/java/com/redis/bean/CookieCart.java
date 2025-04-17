package com.redis.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-17  10:10
 */
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CookieCart {
    private int UserId;

    private int productId;

    private int amount;
}
