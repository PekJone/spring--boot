package org.com;

import org.com.learn.bean.User;
import org.com.learn.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-06  10:29
 */
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private UserService userService;
    @Test
    void test01(){
        User user = userService.findUser();
        System.out.println(user);
    }
}

