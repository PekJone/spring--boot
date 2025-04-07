package org.com.learn.controller;

import org.com.learn.bean.Student;
import org.com.learn.bean.User;
import org.com.learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-05  17:18
 */
@RestController
public class HelloController {

    @Autowired
    private User user;

    @Autowired
    private UserService userService;


    @GetMapping("/hello")
    public String hello(){
        return user.getPassword();
    }

    @Autowired
    private Student student;


    @GetMapping("/hello2")
    public String test02(){
        return student.toString();
    }

    @GetMapping("/hello3")
    public void userTest(){
        userService.save("wang","123",23);
        userService.deleteByName("wang");
        System.out.println("test");
    }


}
