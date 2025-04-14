package org.mybatis.service.Impl;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.mybatis.bean.TransactionOne;
import org.mybatis.mapper.TransactionMapper;
import org.mybatis.service.TransactionService;
import org.springframework.stereotype.Service;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-14  9:17
 */
@Service
public class TransactionImplService extends ServiceImpl<TransactionMapper,TransactionOne> implements TransactionService{

}
