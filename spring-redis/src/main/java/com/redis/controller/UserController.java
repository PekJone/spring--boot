package com.redis.controller;

import com.redis.bean.User;
import com.redis.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-15  9:23
 */
@Api(description = "用户接口")
@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }
    @ApiOperation("数据库初始化100条数据")
    @GetMapping("/init")
    public void initUser(){
//        List<User> list = new ArrayList<>();
//        for (int i=0;i<100;i++){
//            User user = new User();
//            user.setUsername("un"+i).setPassword("un").setSex((byte)(new Random().nextInt(2)));
//            list.add(user);
//        }
//        userService.saveBatch(list);
        String temp = "un";
        for (int i=0;i<100;i++){
            Random random = new Random();
            User user = new User();
            user.setUsername(temp+i).setPassword(temp+i).setSex(((byte)random.nextInt(2)));
            userService.createUser(user);
        }
    }
    @ApiOperation("根据用户ID查询用户信息")
    @GetMapping(value = "/findById/{id}")
    public User findById(@PathVariable int id){
        User user = userService.findUserById(id);
        return user;
    }

    @ApiOperation("更新用户信息")
    @PostMapping(value = "/updateUser")
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }


}
