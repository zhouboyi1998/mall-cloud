package com.cafe.generator.mybatis;

import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.generator.mybatis
 * @Author: zhouboyi
 * @Date: 2022/4/22 14:37
 * @Description: MyBatis Generator 代码生成器
 */
public class MyBatisGenerator {

    /**
     * 生成代码: Model、DAO、Mapper
     */
    public static void generate() {
        try {
            // 存储 MyBatis Generator 执行过程中的报错信息
            List<String> warnings = new ArrayList<String>();
            // 当生成的代码重复时, 覆盖源代码
            boolean overwrite = true;

            // 读取 XML 配置文件
            InputStream inputStream = MyBatisGenerator.class
                .getResourceAsStream("/generator.xml");
            // 创建 MyBatis Generator 配置解析器
            ConfigurationParser configurationParser = new ConfigurationParser(warnings);
            // 使用 MyBatis Generator 配置解析器解析 XML 配置文件
            Configuration configuration = configurationParser.parseConfiguration(inputStream);
            inputStream.close();

            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            // 创建 MyBatis Generator
            org.mybatis.generator.api.MyBatisGenerator myBatisGenerator
                = new org.mybatis.generator.api.MyBatisGenerator(configuration, callback, warnings);
            // 生成代码
            myBatisGenerator.generate(null);

            // 输出警告信息
            for (String warning : warnings) {
                System.out.println(warning);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 使用 MyBatis Generator 生成代码
        generate();
    }
}
