package com.redis.bean;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-14  16:47
 */
@Accessors
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName(value = "user")
public class User {
    @Id
    private int id;
    @TableField(value = "username")
    private String username;
    @TableField(value = "password")
    private String password;
    @TableField(value = "sex")
    private Byte sex;
    @TableField(value = "deleted")
    private Byte deleted;
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    private Date updateTime;

}
