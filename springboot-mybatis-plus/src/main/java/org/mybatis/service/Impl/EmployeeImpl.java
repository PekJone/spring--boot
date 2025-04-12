package org.mybatis.service.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.mybatis.bean.Employee;
import org.mybatis.mapper.EmployeeMapper;
import org.mybatis.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-12  11:17
 */
@Service
public class EmployeeImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService{


    private EmployeeMapper employeeMapper;
    @Autowired
    public void setEmployeeMapper(EmployeeMapper employeeMapper){
        this.employeeMapper = employeeMapper;
    }

    @Override
    public IPage<Employee> myGetAllByGender(IPage iPage, Integer gender) {
        IPage<Employee> list = employeeMapper.myGetAllByGender(iPage,gender);
        return list;
    }
}
