package com.my.entity;

import lombok.Data;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-05-09  17:17
 */
@Data
public class User {
    private Long id; // 雪花id-64位
    private String name; // 姓名
    private int sex; // 性别
}
