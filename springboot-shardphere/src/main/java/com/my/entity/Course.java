package com.my.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-05-12  16:16
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Course {

    private Long cid;
    private String cname;

    private Long userId;

    private String cstatus;
}
