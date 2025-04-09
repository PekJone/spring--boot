package org.com.learn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-09  16:34
 *
 */
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer  {
//
//    //静态资源处理需要重写的方法
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        //使用registry绑定path pattern以及真实的静态资源文件存储路径
//        registry.addResourceHandler("/abc/**") //配置路径访问模式
//                .addResourceLocations("classpath:/static1/","classpath:/static1/");//配置静态资源路径
//
//
//    }
//}

//添加这个注解之后，表示不在使用springboot提供的默认的自动配置
@EnableWebMvc
//一下配置使用自己的 不再基于springboot的配置自动扩站
@Configuration
public class WebConfig implements WebMvcConfigurer {

    //静态资源处理需要重写的方法
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //使用registry绑定path pattern以及真实的静态资源文件存储路径
        registry.addResourceHandler("/abc/**") //配置路径访问模式
                .addResourceLocations("classpath:/static1/", "classpath:/static1/");//配置静态资源路径


    }
}
