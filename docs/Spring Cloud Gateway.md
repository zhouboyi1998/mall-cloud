<h2 align="center">📔 Spring Cloud Gateway</h2>

### 🧰 模块搭建

* 配合 `Spring Security` 使用时
* 不要引入 `Spring Boot` / `Spring Cloud` 提供的 `Spring Security Starter`
* 因为这样会引入 `Spring Boot Web` 依赖
* 和 `Spring Cloud Gateway` 使用的 `Spring Boot WebFlux` 依赖产生冲突
* 按需导入 `Spring Security` 模块即可
