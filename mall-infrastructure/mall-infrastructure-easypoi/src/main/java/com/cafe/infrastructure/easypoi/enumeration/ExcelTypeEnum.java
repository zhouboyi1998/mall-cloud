package com.cafe.infrastructure.easypoi.enumeration;

import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.easypoi.enumeration
 * @Author: zhouboyi
 * @Date: 2025/11/5 17:55
 * @Description: Excel 文件类型枚举
 */
@Getter
@AllArgsConstructor
public enum ExcelTypeEnum {

    XLS(".xls", ExcelType.HSSF),

    XLSX(".xlsx", ExcelType.XSSF),

    ;

    /**
     * Excel 文件扩展名
     */
    final String value;

    /**
     * Excel 文件类型
     */
    final ExcelType type;
}
