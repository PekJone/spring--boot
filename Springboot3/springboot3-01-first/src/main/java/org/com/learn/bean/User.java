package org.com.learn.bean;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-10  9:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
//@JacksonXmlRootElement //将java对象转换成xml文件的字符串
public class User {
    private String username;
    private Integer age;
}
