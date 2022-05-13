<h2 align="center">📔 Spring Cloud Gateway</h2>

### 🧰 模块搭建

* 不能导入 `spring-boot-starter-web` 依赖包
* 注意依赖的其它模块也不能有 web 依赖包
* 如果需要 Web 相关功能，可以选择 `spring-boot-starter-webflux` 依赖包

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
```

* 配合 Spring Security 使用时
* 不要导入 Spring Boot / Spring Cloud 提供的 `starter`
* starter 中有 Web 相关配置，会产生冲突
* 按需导入部分 Spring Security 依赖即可

### 📑 基础知识

* Spring WebFlux：基于 Reactor 的响应式 Web 框架
* Spring Cloud Gateway：使用 WebFlux 编写的非阻塞式微服务网关
