package org.com.learn.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-07  15:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Vip {
    private Long id;
    private String names;

    private String cardNumber;

    private String birth;
}
