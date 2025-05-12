package com.my.service;

import com.my.entity.User;

import java.util.List;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-05-09  17:41
 */
public interface UserService {
    int insert(User user);

    List<User> selectUser();
}
