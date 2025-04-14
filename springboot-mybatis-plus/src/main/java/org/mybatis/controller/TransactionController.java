package org.mybatis.controller;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.mybatis.bean.TransactionOne;
import org.mybatis.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-14  9:20
 */
@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private TransactionService transactionService;

    @Autowired
    public void setTransactionService(TransactionService transactionService){
        this.transactionService = transactionService;
    }
    @GetMapping("/test")
    public String test(){
        System.out.println("this is test");
        return "success";
    }

    @GetMapping("/initData")
    public String initData(){
        List<TransactionOne> list = new ArrayList<>();
        for (int i=0;i<100;i++){
            TransactionOne transactionOne = new TransactionOne();
            transactionOne.setId(IdWorker.getId()).setName("王九"+i).setValue("value").setShowOrder(i);
            list.add(transactionOne);
        }
        transactionService.saveBatch(list);
        return "success";
    }
    @GetMapping("/delete")
    @Transactional(isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
    public String testDeleteTransaction(){
        System.out.println("测试事物");
        transactionService.removeById("01911592873952268292");
        try{
            int i=1/0;
        }catch (Exception e){
            System.out.println(".....");
            //指定回滚操作 必须回归 没有捕获异常也要回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        System.out.println("------");
        return "success";
    }

}
