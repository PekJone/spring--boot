package com.my.mapper;

import com.my.entity.User;

import java.util.List;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-05-09  17:29
 */
public interface UserMapper {
    int insertUser(User user);

    // 查询所有库表数据
    List<User> selectUser();
}
