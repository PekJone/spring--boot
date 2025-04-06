package org.com;

import org.springframework.boot.SpringApplication;
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
         * run方法的第一个参数实际就是一个配置类 对应以前 配置文件
         *
         */

        SpringApplication.run(MyApplication.class);
    }
}