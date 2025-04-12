package org.mybatis.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-12  10:55
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@TableName(value = "tbl_employee")
public class Employee {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private String lastName;

    private String email;

    private Integer gender;

    private Integer age;


}
