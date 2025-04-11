package org.com.learn.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.com.learn.bean.Vip;
import org.com.learn.result.Result;
import org.com.learn.service.VipService;
import org.com.learn.util.Constant;
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
    @GetMapping("/findAll/{pageNo}")
    public Result<PageInfo<Vip>> findAll(@PathVariable int pageNo){

        PageHelper.startPage(pageNo, Constant.PAGE_SIZE);
        List<Vip> vips = vipService.findAll();

        PageInfo<Vip> vipPageInfo = new PageInfo<>(vips);
        return Result.OK(vipPageInfo);
    }

    @GetMapping("/detail")
    public Result<Vip> findByCardNumber(@RequestParam("cardNumber") String cardNumber){
        return Result.OK(vipService.selectByCardNumber(cardNumber));
    }
}
