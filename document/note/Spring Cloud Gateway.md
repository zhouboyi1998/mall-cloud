<h2 align="center">📔 Spring Cloud Gateway</h2>

### 🧰 模块搭建

* 配合 `Spring Security` 使用时，不要直接使用 `starter` 引入所有依赖
* 因为其中引入的 `Web` 依赖会和 `Spring Cloud Gateway` 使用的 `WebFlux` 依赖产生冲突
* 按需导入需要的 `Spring Security` 模块即可
