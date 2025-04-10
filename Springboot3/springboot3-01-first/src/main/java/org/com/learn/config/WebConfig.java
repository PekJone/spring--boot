package org.com.learn.config;

import org.com.learn.component.YamlHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

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

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
       converters.add(new YamlHttpMessageConverter());
    }
}
