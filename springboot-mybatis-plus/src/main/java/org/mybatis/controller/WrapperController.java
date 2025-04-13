package org.mybatis.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.mybatis.bean.Employee;
import org.mybatis.result.Result;
import org.mybatis.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.Query;
import java.util.List;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-12  17:08
 */
@RestController
@RequestMapping("/wrapper")
public class WrapperController {

    EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/queryByName")
    public Result<List<Employee>> queryByName(){
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("last_name","age")
                .eq("last_name","王五97");
        List<Employee> employees = employeeService.list(queryWrapper);
        return Result.OK(employees);
    }




}
