package org.mybatis.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mybatis.bean.Employee;
import org.mybatis.result.Result;
import org.mybatis.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-12  11:15
 */

@RestController
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    @GetMapping("/initData")
    public void initData(){
        List<Employee> list = new ArrayList<>();
        for(int i=0;i<100;i++){
            Employee employee = new Employee();
            employee.setAge(i).setGender(1).setEmail("wangWu"+i+"@163.com").setLastName("王五"+i);
            list.add(employee);
        }
        employeeService.saveBatch(list);
    }


    @GetMapping("/initDataOne")
    public void initDataOne(){
            Employee employee = new Employee();
            employee.setAge(12).setGender(1).setEmail("wangWu"+12+"@163.com").setLastName("王6"+12);
            employeeService.save(employee);
    }


    @GetMapping("/updateOne")
    public void updateOne(){
        Employee employee = new Employee();
        employee.setId(12L).setAge(12).setGender(1).setEmail("wangWu"+12+"@163.com").setLastName("王6"+12);
        employeeService.saveOrUpdate(employee);
    }
    @GetMapping("/detail/{id}")
    public Result<Employee> detail(@PathVariable("id") Long id){
        Employee employee = employeeService.getById(id);
        return Result.OK(employee);
    }

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody Employee employee){
        Boolean result  = employeeService.save(employee);
        return Result.OK(result);
    }

   @GetMapping("/selectList")
   public Result<List<Employee>> selectList(){
       Map<String,Object> columnMap = new HashMap<>();
       columnMap.put("gender",2);
       List<Employee> employeeList = employeeService.listByMap(columnMap);
       return Result.OK(employeeList);
   }

   @GetMapping("/testPage")
   public Result<List<Employee>> testPage(){
       IPage<Employee> iPage = new Page<>(1,8);
       IPage<Employee> page = employeeService.page(iPage);
       List<Employee> records = page.getRecords();
       return Result.OK(records);
   }
   @GetMapping("/myGetAllByGender")
   public Result<List<Employee>> myGetAllByGender(){
        IPage<Employee> iPage = new Page<>(1,8);
        List<Employee> list = employeeService.myGetAllByGender(iPage,2).getRecords();
        return Result.OK(list);
   }

}
