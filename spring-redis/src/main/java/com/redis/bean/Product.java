package com.redis.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-15  17:28
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS,include = JsonTypeInfo.As.PROPERTY,property = "@class")
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName(value = "product")
public class Product {
    @Id
    @TableField(value = "id")
    private int productId;

    private String name;

    private int price;

    private String detail;
}
