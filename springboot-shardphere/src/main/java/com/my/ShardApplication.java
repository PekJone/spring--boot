package com.my;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-05-09  17:36
 */
@SpringBootApplication
@MapperScan("com.my.mapper")
public class ShardApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShardApplication.class,args);
    }
}
