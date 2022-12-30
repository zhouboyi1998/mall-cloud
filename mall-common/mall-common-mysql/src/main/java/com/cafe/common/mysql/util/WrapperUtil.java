package com.cafe.common.mysql.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cafe.common.constant.FieldConstant;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.mysql.util
 * @Author: zhouboyi
 * @Date: 2022/5/1 20:21
 * @Description: MyBatis-Plus 条件构造工具类
 */
public class WrapperUtil {

    /**
     * Wrapper 条件构造时忽略的属性
     */
    private static final List<String> IGNORE_FIELD_LIST = new ArrayList<String>() {{
        add(FieldConstant.SERIAL_VERSION_UID);
    }};

    /**
     * 构造 QueryWrapper 条件
     *
     * @param model
     * @param <T>
     * @return
     */
    public static <T> QueryWrapper<T> createQueryWrapper(T model) {
        return (QueryWrapper<T>) createWrapper(model, new QueryWrapper<T>());
    }

    /**
     * 构造 UpdateWrapper 条件
     *
     * @param model
     * @param <T>
     * @return
     */
    public static <T> UpdateWrapper<T> createUpdateWrapper(T model) {
        return (UpdateWrapper<T>) createWrapper(model, new UpdateWrapper<T>());
    }

    /**
     * 构造 Wrapper 条件
     *
     * @param model
     * @param wrapper
     * @param <T>
     * @return
     */
    public static <T, R, Children extends AbstractWrapper<T, R, Children>> AbstractWrapper<T, R, Children> createWrapper(T model, AbstractWrapper<T, R, Children> wrapper) {
        // 获取所有属性
        Map<String, Field> fieldMap = ReflectUtil.getFieldMap(model.getClass());
        // 迭代器遍历所有属性
        for (Map.Entry<String, Field> entry : fieldMap.entrySet()) {
            // 跳过条件构造时忽略的属性
            if (IGNORE_FIELD_LIST.contains(entry.getKey())) {
                continue;
            }
            // 获取属性
            Field field = entry.getValue();
            // 根据传入的对象和属性, 获取属性值
            Object fieldValue = ReflectUtil.getFieldValue(model, field);
            // 如果属性值不为空, 加入 Wrapper 条件中
            if (ObjectUtil.isNotEmpty(fieldValue)) {
                // 获取属性名
                String fieldName = StrUtil.toUnderlineCase(field.getName());
                // 如果属性是 String 类型, 使用 like 条件, 否则使用 eq 条件
                if (fieldValue instanceof String) {
                    wrapper.like((R) fieldName, fieldValue);
                } else {
                    wrapper.eq((R) fieldName, fieldValue);
                }
            }
        }
        return wrapper;
    }
}
