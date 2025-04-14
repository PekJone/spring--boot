package com.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-14  16:23
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value(value = "true")
    private Boolean swaggerEnabled;

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(swaggerEnabled)
                .groupName("redis组").select()
                .apis(RequestHandlerSelectors.basePackage("com.redis.controller"))
                .paths(PathSelectors.any())
                .build();

    }

    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //标题
                .title("wpf的redisLearn")
                //描述
                .description("redis")
                //许可
                .license("lzl 2021/06/13")
                //许可链接
                .licenseUrl("https://blog.csdn.net/qq_26103133?spm=1001.2101.3001.5343")
                //服务条款网址
                .termsOfServiceUrl("www.spring.io")
                //版本
                .version("1.0")
                //维护人信息
                .contact(new Contact("wpf","www.163.com","742662307@qq.com"))
                .build();
    }

}
