<h2 align="center">📔 Spring Cloud Gateway</h2>

### ⚓ 模块搭建

* 不能导入 spring-boot-starter-web 依赖包
* 注意依赖的其它模块也不能有 web 依赖包
* 可以选择 webflux 依赖包提供 web 相关功能

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
```

### 📑 基础知识

* Spring WebFlux：基于 Reactor 的响应式 Web 框架
* Spring Cloud Gateway：使用 WebFlux 编写的非阻塞式微服务网关
