package com.cafe.elasticsearch.util;

import com.cafe.common.constant.app.FieldConstant;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.elasticsearch.util
 * @Author: zhouboyi
 * @Date: 2025/4/13 0:31
 * @Description: Spring Data ElasticSearch 条件构造工具类
 */
public class DocumentUtil {

    /**
     * Wrapper 条件构造时忽略的属性
     */
    private static final List<String> IGNORE_FIELD_LIST = new ArrayList<>();

    static {
        IGNORE_FIELD_LIST.add(FieldConstant.SERIAL_VERSION_UID);
    }

    /**
     * 构造 Document 修改条件
     *
     * @param currentDocumentData 当前的文档数据
     * @param updateDocumentData  需要修改的文档数据
     * @param <T>                 索引类型
     * @return Document 修改条件
     */
    public static <T> Document updateDocument(T currentDocumentData, T updateDocumentData) {
        Document document = Document.create();
        // 获取所有属性
        Field[] fields = updateDocumentData.getClass().getDeclaredFields();
        // 迭代器遍历所有属性
        for (Field field : fields) {
            String fieldName = field.getName();
            // 跳过条件构造时忽略的属性
            if (IGNORE_FIELD_LIST.contains(fieldName)) {
                continue;
            }
            // 设置允许访问该属性 (反射时默认不允许访问私有的属性、方法、构造器)
            ReflectionUtils.makeAccessible(field);
            // 获取属性值, 优先获取修改值
            Optional.ofNullable(ReflectionUtils.getField(field, updateDocumentData))
                .map(Optional::of)
                // 如果修改值为 null, 获取当前值
                .orElse(Optional.ofNullable(ReflectionUtils.getField(field, currentDocumentData)))
                // 如果可以获取到属性值, 加入 Document 条件中
                .ifPresent(fieldValue -> document.append(fieldName, fieldValue));
        }
        return document;
    }
}
