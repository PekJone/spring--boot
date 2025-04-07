package org.com.learn.service;

import org.com.learn.bean.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-06  10:43
 */
@Service
public interface UserService {
    User findUser();

    void save(String username,String password,Integer age);

    void deleteByName(String username);
}
