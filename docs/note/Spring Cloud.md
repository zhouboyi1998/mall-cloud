<h2 align="center">📔 Spring Cloud</h2>

### 📑 Spring Cloud Gateway

#### 整合 Spring Security

* 整合时应该按需引入 `Spring Security` 的各个子模块
* 不要引入 `spring-boot-starter-security` / `spring-cloud-starter-security`
* 因为完整的 `starter` 包含 `spring-boot-starter-web` 依赖
* 和 `Spring Cloud Gateway` 使用的 `spring-boot-starter-webflux` 依赖会产生冲突
