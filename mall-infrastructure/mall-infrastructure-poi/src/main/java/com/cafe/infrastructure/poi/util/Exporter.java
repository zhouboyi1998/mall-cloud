package com.cafe.infrastructure.poi.util;

import com.cafe.infrastructure.poi.enumeration.ExcelTypeEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.poi.util
 * @Author: zhouboyi
 * @Date: 2025/10/21 16:52
 * @Description: POI 文件导出工具
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Exporter<T> {

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
     * 导出字段过滤规则列表
     */
    private final List<Function<Integer, Boolean>> filters;

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
         * 导出字段过滤规则列表
         */
        private final List<Function<Integer, Boolean>> filters = new ArrayList<>();

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

        @SafeVarargs
        public final Builder<T> filters(Function<Integer, Boolean>... filters) {
            if (filters.length > 0) {
                this.filters.addAll(Stream.of(filters).collect(Collectors.toList()));
            }
            return this;
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
            return new Exporter<>(response, dataList, clazz, filters, filename, fileType, sheetName);
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

        // 解析导出字段列表
        List<Field> fields = resolveFields(clazz, filters);

        // 将文件数据写入到响应的输出流中
        try (Workbook workbook = fileType.getConstructor().get()) {
            // 新建工作表
            Sheet sheet = workbook.createSheet(sheetName);
            // 填充表头数据
            fillHeader(sheet, fields);
            // 填充表格数据
            fillTable(sheet, fields, dataList);
            // 写入数据
            try (OutputStream os = response.getOutputStream()) {
                workbook.write(os);
            }
        } catch (Exception e) {
            throw new RuntimeException("Write data to response output stream failed!", e);
        }
    }

    /**
     * 解析导出字段列表
     *
     * @param clazz   导出数据Class对象
     * @param filters 导出字段过滤规则列表
     * @return 导出字段列表
     */
    private List<Field> resolveFields(Class<T> clazz, List<Function<Integer, Boolean>> filters) {
        // 获取导出数据类型的所有字段
        Stream<Field> stream = FieldUtils.getAllFieldsList(clazz).stream();
        // 根据过滤规则筛选需要导出的字段
        for (Function<Integer, Boolean> filter : filters) {
            stream = stream.filter(field -> filter.apply(field.getModifiers()));
        }
        // 返回导出字段列表
        return stream.collect(Collectors.toList());
    }

    /**
     * 填充表头数据
     *
     * @param sheet  工作表
     * @param fields 导出字段列表
     */
    private void fillHeader(Sheet sheet, List<Field> fields) {
        // 新建行
        Row row = sheet.createRow(0);
        for (int i = 0; i < fields.size(); i++) {
            // 新建单元格
            Cell cell = row.createCell(i);
            // 使用字段名称作为表头名称
            cell.setCellValue(fields.get(i).getName());
        }
    }

    /**
     * 填充表格数据
     *
     * @param sheet    工作表
     * @param fields   导出字段列表
     * @param dataList 导出数据列表
     */
    private void fillTable(Sheet sheet, List<Field> fields, List<T> dataList) throws IllegalAccessException {
        for (int r = 0; r < dataList.size(); r++) {
            // 获取当前行数据
            T data = dataList.get(r);
            // 新建行
            Row row = sheet.createRow(r + 1);
            for (int c = 0; c < fields.size(); c++) {
                // 获取当前单元格数据
                Field field = fields.get(c);
                field.setAccessible(true);
                Object value = field.get(data);
                // 新建单元格
                Cell cell = row.createCell(c);
                // 填充单元格数据
                cell.setCellValue(value.toString());
            }
        }
    }
}
