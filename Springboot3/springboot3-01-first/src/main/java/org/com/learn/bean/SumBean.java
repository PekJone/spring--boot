package org.com.learn.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-07  9:39
 */
@Component
public class SumBean {

    @Autowired
    private Environment environment;

    public void doSome(){
        String[] profiles=environment.getActiveProfiles();
        for (String profile : profiles) {
            System.out.println(profile);
        }

    }

}
