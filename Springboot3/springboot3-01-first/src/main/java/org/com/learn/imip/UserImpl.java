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
}
