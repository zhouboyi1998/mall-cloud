package com.cafe.generator.plus.config.converter;

import com.baomidou.mybatisplus.generator.config.converts.TypeConverts;
import com.baomidou.mybatisplus.generator.config.converts.select.BranchBuilder;
import com.baomidou.mybatisplus.generator.config.converts.select.Selector;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.generator.plus.config.converter
 * @Author: zhouboyi
 * @Date: 2024/3/16 3:15
 * @Description:
 */
public class TypeConverters extends TypeConverts {

    public static Selector<String, IColumnType> use(String param) {
        return new Selector<>(param.toLowerCase());
    }

    public static BranchBuilder<String, IColumnType> contains(CharSequence value) {
        return BranchBuilder.of(s -> s.contains(value));
    }

    public static BranchBuilder<String, IColumnType> containsAny(CharSequence... values) {
        return BranchBuilder.of(s -> {
            for (CharSequence value : values) {
                if (s.contains(value)) {
                    return true;
                }
            }
            return false;
        });
    }
}
