package org.com.learn.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-06  15:24
 */
@Service
public class SystemService {
    @Value("${spring.datasource.username}")
    private String  name;

    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.type}")
    private String path ;

    public void info(){
        System.out.println(name);
        System.out.println(password);
        System.out.println(path);

    }

}
