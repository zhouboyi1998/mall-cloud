package com.cafe.common.mysql.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.cafe.common.constant.FieldConstant;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.mysql.handler
 * @Author: zhouboyi
 * @Date: 2023/3/29 11:39
 * @Description: 自定义 MyBatis-Plus 字段填充处理器
 */
@Component
public class MetaObjectHandlerImpl implements MetaObjectHandler {

    private final Clock clock;

    @Autowired
    public MetaObjectHandlerImpl(Clock clock) {
        this.clock = clock;
    }

    /**
     * 新增数据的字段填充处理
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now(clock);
        setFieldValByName(FieldConstant.CREATE_TIME, now, metaObject);
        setFieldValByName(FieldConstant.UPDATE_TIME, now, metaObject);
    }

    /**
     * 更新数据的字段填充处理
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName(FieldConstant.UPDATE_TIME, LocalDateTime.now(clock), metaObject);
    }
}
