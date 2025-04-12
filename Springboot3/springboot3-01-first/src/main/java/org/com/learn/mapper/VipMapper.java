package org.com.learn.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.com.learn.bean.Vip;

import java.util.List;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-07  15:26
 */

@Mapper
public interface VipMapper {
    /**
     * 保存信息
     * @param vip
     * @return
     */
    int insert(Vip vip);

    /**
     * 获取所有会员信息
     * @return
     */
    List<Vip>  selectAll();

    Vip selectByCardNumber(String cardNumber);
}
