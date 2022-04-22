package com.cafe.generator.plus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

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

    /**
     * 配置文件
     */
    private static Properties properties;

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
            e.printStackTrace();
        }
    }

    public static void generate() {

        // 1. 创建代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();

        // 2. 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        // 项目路径
        globalConfig.setOutputDir("mall-code-generator/src/main/java");
        // 作者
        globalConfig.setAuthor("zhouboyi");
        // 去掉 Service 接口的前缀 I
        globalConfig.setServiceName("%sService");
        // 主键策略为自增
        globalConfig.setIdType(IdType.AUTO);
        // 开启 Swagger 模式
        globalConfig.setSwagger2(true);
        // 生成后是否打开资源管理器
        globalConfig.setOpen(false);

        // 3. 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        // 数据库类型
        dataSourceConfig.setDbType(DbType.MYSQL);
        // 驱动
        dataSourceConfig.setDriverName(properties.getProperty("driver"));
        // 地址
        dataSourceConfig.setUrl(properties.getProperty("url"));
        // 用户名
        dataSourceConfig.setUsername(properties.getProperty("username"));
        // 密码
        dataSourceConfig.setPassword(properties.getProperty("password"));

        // 4. 生成路径配置
        PackageConfig packageConfig = new PackageConfig();
        // 父路径
        packageConfig.setParent(properties.getProperty("/"));
        // Model 生成路径
        packageConfig.setEntity(properties.getProperty("model-package"));
        // Mapper 映射文件生成路径
        packageConfig.setXml(properties.getProperty("mapper-package"));
        // DAO 接口生成路径
        packageConfig.setMapper(properties.getProperty("dao-package"));
        // Service 接口生成路径
        packageConfig.setService(properties.getProperty("service-package"));
        // Service 实现类生成路径
        packageConfig.setServiceImpl(properties.getProperty("service-impl-package"));
        // Controller 生成路径
        packageConfig.setController(properties.getProperty("controller-package"));

        // 5. 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        // 类命名规则: 下划线转驼峰
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        // 字段命名规则: 下划线转驼峰
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        // 去除表名前缀
        strategyConfig.setTablePrefix("mall_");
        // 逻辑删除字段名
        strategyConfig.setLogicDeleteFieldName("is_deleted");
        // 去除布尔类型的 is 前缀, 类型为 tinyint(1)
        strategyConfig.setEntityBooleanColumnRemoveIsPrefix(true);
        // REST API 风格
        strategyConfig.setRestControllerStyle(true);

        // 6. 生成器加载配置
        autoGenerator.setGlobalConfig(globalConfig);
        autoGenerator.setDataSource(dataSourceConfig);
        autoGenerator.setPackageInfo(packageConfig);
        autoGenerator.setStrategy(strategyConfig);

        // 7. 生产代码
        autoGenerator.execute();
    }

    public static void main(String[] args) {
        generate();
    }
}
