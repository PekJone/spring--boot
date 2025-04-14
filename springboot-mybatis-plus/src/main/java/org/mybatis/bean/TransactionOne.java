package org.mybatis.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-12  15:32
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@TableName(value = "transaction_one")
public class TransactionOne {

    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @TableField(value = "name")
    private String name;
    @TableField(value = "value")
    private String value;
    @TableField(value = "show_order")
    private int showOrder;
}
