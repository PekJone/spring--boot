package org.com.learn.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-06  17:41
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "app2.abc")
public class MapBean {

    private String[] names;

    private Address[] addresses;

    private List<Address>  addressList;

    private Map<String,Address> addrs ;
}
