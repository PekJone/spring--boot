package com.my.service.impl;

import com.my.entity.User;
import com.my.mapper.UserMapper;
import com.my.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-05-09  17:42
 */
@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public int insert(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public List<User> selectUser() {
        return userMapper.selectUser();
    }
}
