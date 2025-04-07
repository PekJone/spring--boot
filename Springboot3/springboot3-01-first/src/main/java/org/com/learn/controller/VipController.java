package org.com.learn.controller;

import org.com.learn.bean.Vip;
import org.com.learn.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-07  15:47
 */
@RestController
@RequestMapping("/vip")
public class VipController {

    @Autowired
    private VipService vipService;

    @PostMapping("/save")
    public boolean saveVip(@RequestBody Vip vip){
        return vipService.save(vip);
    }
    @GetMapping("/findAll")
    public List<Vip> findAll(){
        List<Vip> vips = vipService.findAll();
        for (Vip vip: vips) {
            System.out.println(vip.toString());
        }
        return vips;
    }
}
