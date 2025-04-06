package org.com.learn.controller;

import org.com.learn.bean.MapBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-06  17:41
 */
@RestController
public class MapController {

    @Autowired
    private MapBean mapBean;
    @GetMapping("/map/hello")
    public String hello(){
        return mapBean.toString();
    }
}
