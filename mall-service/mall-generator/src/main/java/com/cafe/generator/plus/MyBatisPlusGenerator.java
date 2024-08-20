package com.cafe.generator.plus;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.cafe.common.constant.database.DatabaseConstant;
import com.cafe.generator.plus.config.builder.DataSourceConfigBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.generator.plus
 * @Author: zhouboyi
 * @Date: 2022/4/22 15:37
 * @Description: MyBatis-Plus 代码生成器
 */
@Slf4j
public class MyBatisPlusGenerator {

    private static final String DATABASE_TYPE = "mysql";

    /**
     * 配置文件
     */
    private static Properties properties;

    static {
        try {
            // 创建 properties 对象
            properties = new Properties();
            // 获取配置文件
            String fileName = "generator-" + DATABASE_TYPE + ".properties";
            InputStream inputStream = Optional.ofNullable(MyBatisPlusGenerator.class.getClassLoader().getResourceAsStream(fileName))
                .orElseThrow(NullPointerException::new);
            // 将配置文件加载到 properties 对象中
            properties.load(inputStream);
            // 关闭输入流
            inputStream.close();
        } catch (Exception e) {
            log.error("MyBatisPlusGenerator: Failed to create properties! message -> {}", e.getMessage(), e);
            System.exit(1);
        }
    }

    /**
     * 字段填充策略
     */
    public static final List<TableFill> TABLE_FILL_LIST = new ArrayList<>(2);

    static {
        TABLE_FILL_LIST.add(new TableFill(DatabaseConstant.Column.CREATE_TIME, FieldFill.INSERT));
        TABLE_FILL_LIST.add(new TableFill(DatabaseConstant.Column.UPDATE_TIME, FieldFill.INSERT_UPDATE));
    }

    /**
     * 生成代码
     */
    public static void generate() {
        // 1. 全局配置
        GlobalConfig globalConfig = new GlobalConfig()
            // 生成文件的输出目录
            .setOutputDir(properties.getProperty("generate-directory"))
            // 是否覆盖已有文件
            .setFileOverride(false)
            // 生成后是否打开输出目录
            .setOpen(false)
            // 开发人员
            .setAuthor("zhouboyi")
            // 是否开启 Kotlin 模式
            .setKotlin(false)
            // 是否开启 Swagger2 模式
            .setSwagger2(true)
            // 是否开启 ActiveRecord 模式
            .setActiveRecord(false)
            // 是否在 Mapper XML 中添加二级缓存配置
            .setEnableCache(false)
            // 是否在 Mapper XML 中生成通用查询映射结果
            .setBaseResultMap(true)
            // 是否在 Mapper XML 中生成通用查询结果列
            .setBaseColumnList(false)
            // 主键生成策略 (ID 生成器分配主键)
            .setIdType(IdType.ASSIGN_ID)
            // 时间类型对应策略
            .setDateType(DateType.TIME_PACK)
            // Model 命名方式
            .setEntityName("%s")
            // Mapper 命名方式
            .setMapperName("%sMapper")
            // Mapper XML 命名方式
            .setXmlName("%sMapper")
            // Service 命名方式
            .setServiceName("%sService")
            // Service Impl 命名方式
            .setServiceImplName("%sServiceImpl")
            // Controller 命名方式
            .setControllerName("%sController");

        // 2. 数据源配置
        DataSourceConfig dataSourceConfig = DataSourceConfigBuilder.build(DATABASE_TYPE, properties)
            // 数据库连接配置
            .setDriverName(properties.getProperty("driver"))
            .setUrl(properties.getProperty("url"))
            .setUsername(properties.getProperty("username"))
            .setPassword(properties.getProperty("password"))
            .setSchemaName(properties.getProperty("schema"));

        // 3. 生成路径配置
        PackageConfig packageConfig = new PackageConfig()
            .setParent(properties.getProperty("module-package"))
            .setEntity(properties.getProperty("model-package"))
            .setMapper(properties.getProperty("mapper-package"))
            .setXml(properties.getProperty("xml-package"))
            .setService(properties.getProperty("service-package"))
            .setServiceImpl(properties.getProperty("service-impl-package"))
            .setController(properties.getProperty("controller-package"));

        // 4. 策略配置
        StrategyConfig strategyConfig = new StrategyConfig()
            // 数据库表是否大写命名
            .setCapitalMode(false)
            // 是否跳过视图
            .setSkipView(false)
            // 数据库表映射到实体的命名策略 (下划线转驼峰)
            .setNaming(NamingStrategy.underline_to_camel)
            // 数据库表字段映射到实体属性的命名策略 (下划线转驼峰)
            .setColumnNaming(NamingStrategy.underline_to_camel)
            // 表前缀
            .setTablePrefix(properties.getProperty("table-prefix"))
            // 逻辑删除属性名称
            .setLogicDeleteFieldName(DatabaseConstant.Column.IS_DELETED)
            // Boolean 类型字段是否移除 is 前缀
            .setEntityBooleanColumnRemoveIsPrefix(true)
            // 是否生成序列化ID
            .setEntitySerialVersionUID(true)
            // 是否生成字段常量
            .setEntityColumnConstant(false)
            // 是否生成链式 Model
            .setChainModel(true)
            // 是否生成 Lombok Model
            .setEntityLombokModel(true)
            // 是否生成 RESTful 风格 Controller
            .setRestControllerStyle(true)
            // 请求路径格式 (true: 连字符, false: 驼峰)
            .setControllerMappingHyphenStyle(true)
            // 设置字段填充策略
            .setTableFillList(TABLE_FILL_LIST);

        // 5. 模板配置
        TemplateConfig templateConfig = new TemplateConfig()
            // 使用自定义的 Velocity 模板
            .setEntity("/templates/entity.java.vm")
            .setMapper("/templates/mapper.java.vm")
            .setXml("/templates/mapper.xml.vm")
            .setService("/templates/service.java.vm")
            .setServiceImpl("/templates/serviceImpl.java.vm")
            .setController("/templates/controller.java.vm");

        // 6. 创建代码生成器, 加载配置, 执行生成代码
        new AutoGenerator()
            .setGlobalConfig(globalConfig)
            .setDataSource(dataSourceConfig)
            .setPackageInfo(packageConfig)
            .setStrategy(strategyConfig)
            .setTemplate(templateConfig)
            .execute();
    }

    public static void main(String[] args) {
        // 使用 MyBatis-Plus Generator 生成代码
        generate();
    }
}
