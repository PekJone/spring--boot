package org.mybatis.compoent;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-13  9:58
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //在插入时需要创建时间和修改时间
        this.setFieldValByName("createDate",new Date(),metaObject);
        this.setFieldValByName("modifyDate",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("modifyDate",new Date(),metaObject);
    }
}
