package com.cafe.generator.plus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(MyBatisPlusGenerator.class);

    /**
     * 配置文件
     */
    private static Properties properties;

    static {
        try {
            // 创建 properties 对象
            properties = new Properties();
            // 获取配置文件
            InputStream inputStream = MyBatisPlusGenerator.class.getClassLoader().getResourceAsStream("generator.properties");
            // 将配置文件加载到 properties 对象中
            properties.load(inputStream);
            // 关闭输入流
            assert inputStream != null;
            inputStream.close();
        } catch (Exception e) {
            LOGGER.error("MyBatisPlusGenerator: failed to create properties -> {}", e.getMessage());
        }
    }

    public static void generate() {
        // 1. 全局配置
        GlobalConfig globalConfig = new GlobalConfig()
            // 生成文件的输出目录
            .setOutputDir("mall-generator/src/main/java")
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
        DataSourceConfig dataSourceConfig = new DataSourceConfig()
            // 数据库类型
            .setDbType(DbType.MYSQL)
            // 数据库连接配置
            .setUrl(properties.getProperty("url-prefix") + properties.getProperty("module") + properties.getProperty("url-suffix"))
            .setDriverName(properties.getProperty("driver"))
            .setUsername(properties.getProperty("username"))
            .setPassword(properties.getProperty("password"))
            // 指定 MySQL 数据类型和 Java 数据类型的映射
            .setTypeConvert(new MySqlTypeConvert() {
                @Override
                public IColumnType processTypeConvert(GlobalConfig config, String fieldType) {
                    if (fieldType.toUpperCase().contains(MySQLTypeEnum.DECIMAL.toString())) {
                        return DbColumnType.BIG_DECIMAL;
                    } else if (fieldType.toUpperCase().contains(MySQLTypeEnum.DATETIME.toString())) {
                        return DbColumnType.LOCAL_DATE_TIME;
                    }
                    return super.processTypeConvert(config, fieldType);
                }
            });

        // 代码生成路径前缀拼接
        String prefix = properties.getProperty("project-package") + "." + properties.getProperty("module") + ".";
        // 3. 生成路径配置
        PackageConfig packageConfig = new PackageConfig()
            // 父路径
            .setParent(properties.getProperty("com.cafe"))
            // 代码生成路径
            .setEntity(prefix + properties.getProperty("model-package"))
            .setMapper(prefix + properties.getProperty("dao-package"))
            .setXml(prefix + properties.getProperty("mapper-package"))
            .setService(prefix + properties.getProperty("service-package"))
            .setServiceImpl(prefix + properties.getProperty("service-impl-package"))
            .setController(prefix + properties.getProperty("controller-package"));

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
            .setTablePrefix("mall_")
            // 逻辑删除属性名称
            .setLogicDeleteFieldName("is_deleted")
            // Boolean 类型字段是否移除 is 前缀
            .setEntityBooleanColumnRemoveIsPrefix(true)
            // 是否生成序列化ID
            .setEntitySerialVersionUID(true)
            // 是否生成字段常量
            .setEntityColumnConstant(false)
            // 是否生成链式 Model
            .setChainModel(true)
            // 是否生成 Lombok Model
            .setEntityLombokModel(false)
            // 是否生成 RESTful 风格 Controller
            .setRestControllerStyle(true)
            // 请求路径格式 (true: 连字符, false: 驼峰)
            .setControllerMappingHyphenStyle(true);

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
