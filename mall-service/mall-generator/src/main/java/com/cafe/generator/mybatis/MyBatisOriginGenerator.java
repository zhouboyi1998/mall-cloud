package com.cafe.generator.mybatis;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.generator.mybatis
 * @Author: zhouboyi
 * @Date: 2022/4/22 14:37
 * @Description: MyBatis Generator 代码生成器
 */
public class MyBatisOriginGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyBatisOriginGenerator.class);

    /**
     * 生成代码: Model、Mapper
     */
    public static void generate() {
        try {
            // 存储 MyBatis Generator 执行过程中的报错信息
            List<String> warns = new ArrayList<>();

            // 读取 XML 配置文件
            InputStream inputStream = Optional.ofNullable(MyBatisOriginGenerator.class.getClassLoader().getResourceAsStream("generator.xml"))
                .orElseThrow(NullPointerException::new);
            // 创建 MyBatis Generator 配置解析器
            ConfigurationParser configurationParser = new ConfigurationParser(warns);
            // 使用 MyBatis Generator 配置解析器解析 XML 配置文件
            Configuration configuration = configurationParser.parseConfiguration(inputStream);
            // 关闭输入流
            inputStream.close();

            // true: 当生成的代码重复时, 覆盖源代码
            DefaultShellCallback callback = new DefaultShellCallback(true);
            // 创建 MyBatis Generator
            MyBatisGenerator mybatisGenerator = new MyBatisGenerator(configuration, callback, warns);
            // 生成代码
            mybatisGenerator.generate(null);

            // 输出警告信息
            for (String warn : warns) {
                LOGGER.warn("MyBatisGenerator.generate(): warn -> {}", warn);
            }
        } catch (Exception e) {
            LOGGER.error("MyBatisGenerator.generate(): failed to generate code -> {}", e.getMessage());
        }
    }

    public static void main(String[] args) {
        // 使用 MyBatis Generator 生成代码
        generate();
    }
}
