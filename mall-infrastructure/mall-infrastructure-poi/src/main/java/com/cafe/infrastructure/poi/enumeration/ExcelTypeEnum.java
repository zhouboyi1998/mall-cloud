package com.cafe.infrastructure.poi.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.function.Supplier;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.poi.enumeration
 * @Author: zhouboyi
 * @Date: 2025/11/6 18:08
 * @Description: Excel 文件类型枚举
 */
@Getter
@AllArgsConstructor
public enum ExcelTypeEnum {

    XLS(".xls", HSSFWorkbook::new),

    XLSX(".xlsx", XSSFWorkbook::new),

    ;

    /**
     * Excel 文件扩展名
     */
    final String value;

    /**
     * Workbook 构造函数
     */
    final Supplier<Workbook> constructor;
}
