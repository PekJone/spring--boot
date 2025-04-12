package org.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 王朋飞
 * @version 1.0
 * @date ${YEAR}-${MONTH}-${DAY}  ${TIME}
 */
@MapperScan(basePackages = "org.mybatis.mapper")
@SpringBootApplication
public class QuickStartApplication {
    public static void main(String[] args) {

        SpringApplication.run(QuickStartApplication.class,args);
    }
}