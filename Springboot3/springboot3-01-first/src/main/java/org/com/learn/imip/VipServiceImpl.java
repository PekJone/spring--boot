package org.com.learn.imip;

import org.com.learn.bean.Vip;
import org.com.learn.mapper.VipMapper;
import org.com.learn.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-07  15:37
 */
@Service
public class VipServiceImpl implements VipService {
    @Autowired
    private VipMapper vipMapper;
    @Override
    public boolean save(Vip vip) {
        return vipMapper.insert(vip)==1;
    }

    @Override
    public List<Vip> findAll() {
        return vipMapper.selectAll();
    }
}
