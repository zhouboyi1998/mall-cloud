package com.cafe.infrastructure.easypoi.util;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.cafe.infrastructure.easypoi.enumeration.ExcelTypeEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
 * @Package: com.cafe.infrastructure.easypoi.util
 * @Author: zhouboyi
 * @Date: 2025/10/22 09:50
 * @Description: EasyPOI 文件导出工具
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
        // 转换成表头-字段名映射列表
        List<ExcelExportEntity> exportEntityList = fields.stream()
            .map(field -> new ExcelExportEntity(field.getName(), field.getName()))
            .collect(Collectors.toList());

        // 新建导出参数
        ExportParams exportParams = new ExportParams();
        // 设置文件类型
        exportParams.setType(fileType.getType());
        // 设置工作表名
        exportParams.setSheetName(sheetName);

        // 将文件数据写入到响应的输出流中
        try (Workbook workbook = ExcelExportUtil.exportExcel(exportParams, exportEntityList, dataList)) {
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
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
}
