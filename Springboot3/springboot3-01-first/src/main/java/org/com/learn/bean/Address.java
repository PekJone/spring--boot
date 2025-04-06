package org.com.learn.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.context.annotation.Configuration;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-06  17:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@ToString
public class Address {
    private String city;
    private String street;

}
