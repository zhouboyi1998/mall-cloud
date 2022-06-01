package com.cafe.generator.plus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.generator.plus
 * @Author: zhouboyi
 * @Date: 2022/4/22 15:37
 * @Description: MyBatis-Plus Generator 代码生成器
 */
public class MyBatisPlusGenerator {

    private static Logger LOGGER = LoggerFactory.getLogger(MyBatisPlusGenerator.class);

    /**
     * 配置文件
     */
    private static Properties properties;

    /**
     * MySQL 类型枚举
     */
    private enum MySqlType {DECIMAL, DATETIME}

    static {
        try {
            // 创建 properties 对象
            properties = new Properties();
            // 获取配置文件
            InputStream inputStream = MyBatisPlusGenerator.class
                .getClassLoader()
                .getResourceAsStream("generator.properties");
            // 将配置文件加载到 properties 对象中
            properties.load(inputStream);
            // 关闭输入流
            inputStream.close();
        } catch (Exception e) {
            LOGGER.error("MyBatisPlusGenerator failed to create properties: {}", e.getMessage());
        }
    }

    public static void generate() {

        // 1. 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        // 项目路径
        globalConfig.setOutputDir("mall-generator/src/main/java");
        // 作者
        globalConfig.setAuthor("zhouboyi");
        // 去掉 Service 接口的前缀 "I"
        globalConfig.setServiceName("%sService");
        // 主键策略为自增
        globalConfig.setIdType(IdType.AUTO);
        // 生成 BaseResultMap
        globalConfig.setBaseResultMap(true);
        // 生成通用查询结果列
        globalConfig.setBaseColumnList(true);
        // 开启 Swagger 模式
        globalConfig.setSwagger2(true);
        // 生成后是否打开资源管理器
        globalConfig.setOpen(false);

        // 2. 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        // 数据库类型
        dataSourceConfig.setDbType(DbType.MYSQL);
        // 数据库信息
        dataSourceConfig.setDriverName(properties.getProperty("driver"));
        dataSourceConfig.setUrl(properties.getProperty("url-prefix") + properties.getProperty("module") + properties.getProperty("url-suffix"));
        dataSourceConfig.setUsername(properties.getProperty("username"));
        dataSourceConfig.setPassword(properties.getProperty("password"));
        // 指定 MySQL 类型和 Java 类型的映射
        dataSourceConfig.setTypeConvert(new MySqlTypeConvert() {
            @Override
            public IColumnType processTypeConvert(GlobalConfig config, String fieldType) {
                if (fieldType.toUpperCase().contains(MySqlType.DECIMAL.toString())) {
                    return DbColumnType.DOUBLE;
                } else if (fieldType.toUpperCase().contains(MySqlType.DATETIME.toString())) {
                    return DbColumnType.LOCAL_DATE_TIME;
                }
                return (DbColumnType) super.processTypeConvert(config, fieldType);
            }
        });

        // 3. 生成路径配置
        PackageConfig packageConfig = new PackageConfig();
        // 父路径
        packageConfig.setParent(properties.getProperty("/"));
        // 路径前缀拼接
        String prefix = properties.getProperty("project-package") + "." + properties.getProperty("module") + ".";
        // 代码生成路径
        packageConfig.setEntity(prefix + properties.getProperty("model-package"));
        packageConfig.setMapper(prefix + properties.getProperty("dao-package"));
        packageConfig.setXml(prefix + properties.getProperty("mapper-package"));
        packageConfig.setService(prefix + properties.getProperty("service-package"));
        packageConfig.setServiceImpl(prefix + properties.getProperty("service-impl-package"));
        packageConfig.setController(prefix + properties.getProperty("controller-package"));

        // 4. 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        // 类命名规则: 下划线转驼峰
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        // 字段命名规则: 下划线转驼峰
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        // 去除表名前缀
        strategyConfig.setTablePrefix("mall_");
        // 逻辑删除字段名
        strategyConfig.setLogicDeleteFieldName("is_deleted");
        // 去除布尔类型的 is 前缀, 类型为 tinyint(1) 才能成功去除
        strategyConfig.setEntityBooleanColumnRemoveIsPrefix(true);
        // REST API 风格
        strategyConfig.setRestControllerStyle(true);

        // 5. 模板配置
        TemplateConfig templateConfig = new TemplateConfig();
        // 使用自定义模板
        templateConfig.setEntity("/templates/entity.java.vm");
        templateConfig.setMapper("/templates/mapper.java.vm");
        templateConfig.setXml("/templates/mapper.xml.vm");
        templateConfig.setService("/templates/service.java.vm");
        templateConfig.setServiceImpl("/templates/serviceImpl.java.vm");
        templateConfig.setController("/templates/controller.java.vm");

        // 6. 创建代码生成器, 加载配置, 执行生成代码
        new AutoGenerator()
            .setGlobalConfig(globalConfig)
            .setDataSource(dataSourceConfig)
            .setPackageInfo(packageConfig)
            .setStrategy(strategyConfig)
            .execute();
    }

    public static void main(String[] args) {
        // 使用 MyBatis-Plus Generator 生成代码
        generate();
    }
}
