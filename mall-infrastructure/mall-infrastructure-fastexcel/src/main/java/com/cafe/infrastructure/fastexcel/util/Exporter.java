package com.cafe.infrastructure.fastexcel.util;

import cn.idev.excel.EasyExcel;
import cn.idev.excel.converters.longconverter.LongStringConverter;
import cn.idev.excel.support.ExcelTypeEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.fastexcel.util
 * @Author: zhouboyi
 * @Date: 2025/10/21 15:14
 * @Description: FastExcel 文件导出工具
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Exporter<T> {

    /**
     * Long -> String 类型转换器
     */
    public static final LongStringConverter LONG_STRING_CONVERTER = new LongStringConverter();

    /**
     * HttpServletResponse
     */
    private final HttpServletResponse response;

    /**
     * 导出数据列表
     */
    private final List<T> dataList;

    /**
     * 导出数据Class对象
     */
    private final Class<T> clazz;

    /**
     * Excel 文件名
     */
    private final String filename;

    /**
     * Excel 文件类型
     */
    private final ExcelTypeEnum fileType;

    /**
     * Excel 工作表名
     */
    private final String sheetName;

    public static <T> Builder<T> builder(HttpServletResponse response, List<T> dataList, Class<T> clazz) {
        return new Builder<>(response, dataList, clazz);
    }

    public static class Builder<T> {

        /**
         * HttpServletResponse
         */
        private final HttpServletResponse response;

        /**
         * 导出数据列表
         */
        private final List<T> dataList;

        /**
         * 导出数据Class对象
         */
        private final Class<T> clazz;

        /**
         * Excel 文件名
         */
        private String filename = "export_file";

        /**
         * Excel 文件类型
         */
        private ExcelTypeEnum fileType = ExcelTypeEnum.XLSX;

        /**
         * Excel 工作表名
         */
        private String sheetName = "export_sheet";

        private Builder(HttpServletResponse response, List<T> dataList, Class<T> clazz) {
            this.response = response;
            this.dataList = dataList;
            this.clazz = clazz;
        }

        public Builder<T> filename(String filename) {
            if (StringUtils.hasText(filename)) {
                this.filename = filename;
            }
            return this;
        }

        public Builder<T> fileType(ExcelTypeEnum fileType) {
            if (Objects.nonNull(fileType)) {
                this.fileType = fileType;
            }
            return this;
        }

        public Builder<T> sheetName(String sheetName) {
            if (StringUtils.hasText(sheetName)) {
                this.sheetName = sheetName;
            }
            return this;
        }

        public Exporter<T> build() {
            return new Exporter<>(response, dataList, clazz, filename, fileType, sheetName);
        }
    }

    /**
     * 导出数据到 Excel 文件中
     */
    public void export() {
        // 设置响应内容: Excel 文件
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        // 设置响应内容的字符编码: UTF-8
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        // 设置浏览器如何处理响应内容: attachment 下载
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename*=UTF-8''" + filename + fileType.getValue());

        // 将文件数据写入到响应的输出流中
        try {
            EasyExcel.write(response.getOutputStream(), clazz)
                // 注册类型转换器
                .registerConverter(LONG_STRING_CONVERTER)
                // 设置文件类型
                .excelType(fileType)
                // 设置工作表名
                .sheet(sheetName)
                // 写入数据
                .doWrite(dataList);
        } catch (IOException e) {
            throw new RuntimeException("Write data to response output stream failed!", e);
        }
    }
}
