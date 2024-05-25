<h2 align="center">📔 Spring Security</h2>

### 📑 基础知识

#### 配置文件

* 自动装配时排除 `JDBC` 数据源配置

```
spring.autoconfigure.exclude: org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
```

* 允许同名 `Bean` 覆盖

```
spring.main.allow-bean-definition-overriding: true
```

#### 配置类

* `@Value` 注解不允许配置为空，找不到配置会报错
* `@ConfigurationProperties` 注解允许配置为空，找不到匹配的配置时，会使用默认值
