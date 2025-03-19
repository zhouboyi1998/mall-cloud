package com.cafe.infrastructure.mybatisplus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.cafe.common.constant.app.FieldConstant;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.mybatisplus.handler
 * @Author: zhouboyi
 * @Date: 2023/3/29 11:39
 * @Description: MyBatis-Plus 字段填充处理器实现类
 */
@RequiredArgsConstructor
@Component
public class MetaHandler implements MetaObjectHandler {

    private final Clock clock;

    /**
     * 新增数据的字段填充处理
     *
     * @param metaObject 填充对象
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
     * @param metaObject 填充对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName(FieldConstant.UPDATE_TIME, LocalDateTime.now(clock), metaObject);
    }
}
