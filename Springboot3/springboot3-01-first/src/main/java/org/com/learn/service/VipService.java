package org.com.learn.service;

import org.com.learn.bean.Vip;

import java.util.List;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-07  15:36
 */
public interface VipService {
    boolean save(Vip vip);

    List<Vip> findAll();


    Vip selectByCardNumber(String cardNumber);
}
