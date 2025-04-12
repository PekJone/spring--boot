package org.mybatis.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.mybatis.bean.Employee;
import org.mybatis.mapper.EmployeeMapper;
import org.mybatis.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-12  11:17
 */
@Service
public class EmployeeImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService{

}
