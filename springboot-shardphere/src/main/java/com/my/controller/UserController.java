package com.my.controller;

import com.my.entity.User;
import com.my.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-05-09  17:38
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/add")
    public void addUser() {
        User user = new User();
        for (int i = 0; i < 100; i++) {
            user.setName("小明" + i);
            user.setSex(i);
            if (userService.insert(user) == 1) {
                System.out.println("插入成功！");
            } else {
                System.out.println("插入失败！");
            }
        }
    }

    @GetMapping("/select")
    public void getUser() {
        List<User> users = userService.selectUser();
        if (users == null) {
            System.out.println("查询失败！结果为null");
        } else {
            for (User user : users) {
                System.out.println(user);
            }
        }
    }

}
