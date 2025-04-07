package org.com.learn.imip;

import org.com.learn.bean.User;
import org.com.learn.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-06  10:44
 */
@Service
public class UserImpl implements UserService {
    User user = null;
    @Override
    public User findUser() {
        return user;
    }

    @Override
    public void save(String username, String password, Integer age) {
        System.out.println("正在保存用户信息"+username);
    }

    @Override
    public void deleteByName(String username) {
        System.out.println("正在删除用户信息"+username);
    }
}
