package org.com.learn.service;

import org.com.learn.bean.User;
import org.com.learn.bean.UserTest;
import org.springframework.stereotype.Service;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-06  10:43
 */
@Service
public interface UserService {
    UserTest findUser();

    void save(String username,String password,Integer age);

    void deleteByName(String username);

    User findUserById();
}
