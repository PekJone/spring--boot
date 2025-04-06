package org.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 王朋飞
 * @version 1.0
 * @date ${YEAR}-${MONTH}-${DAY}  ${TIME}
 */

@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) {
        /**
         * @EnableAutoConfiguration 注解 启用自动配置  默认自动启用自动配置
         * 自动配置：
         *     所谓的自动配置，sprongboot应用会去类路径当中查找class  根据路径中有哪些类 来自动管理bean
         * run方法的第一个参数实际就是一个配置类 对应以前 配置文件
         *
         */

        SpringApplication.run(MyApplication.class);
    }
}