package com.redis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.redis.bean.User;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-15  9:24
 */
public interface UserService extends IService<User> {
    void createUser(User user);
    void updateUser(User user);

    User findUserById(Integer id);
}
