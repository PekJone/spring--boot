package org.com;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author 王朋飞
 * @version 1.0
 * @date ${YEAR}-${MONTH}-${DAY}  ${TIME}
 */
@Slf4j
@MapperScan(basePackages = {"org.com.learn.mapper"})
@EnableAutoConfiguration
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
        log.trace("trace级别的日志");
        log.debug("debug级别的日志");
        log.info("info级别的日志");
        log.warn("warn级别的日志");
        log.error("error级别的日志");
//        SpringApplication springApplication = new SpringApplication(MyApplication.class);
//        springApplication.setBannerMode(Banner.Mode.OFF);
//        springApplication.run(args);


//        new SpringApplicationBuilder().sources(MyApplication.class).bannerMode(Banner.Mode.OFF).run(args);
    }
}