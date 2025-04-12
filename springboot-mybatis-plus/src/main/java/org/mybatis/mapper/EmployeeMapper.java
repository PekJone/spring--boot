package org.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mybatis.bean.Employee;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-12  10:59
 */
public interface EmployeeMapper  extends BaseMapper<Employee> {
   IPage<Employee> myGetAllByGender(IPage iPage,Integer gender);
}
