<h2 align="center">📔 Java</h2>

### 📑 Annotation

```
// 生成 Java 文档时, 文档只会记录有 @Documented 修饰的注解
@Documented
```

<br/>

```
// 注解保留策略
// RetentionPolicy.SOURCE: 只保留在 .java 源文件中, 编译成 .class 字节码文件后就会删除
// RetentionPolicy.CLASS: 保留到 .class 字节码文件中, 但不加载到 JVM 中参与运行
// RetentionPolicy.RUNTIME: 一直保留到运行阶段, JVM 加载 .class 字节码文件后, 注解仍然保留
@Retention(RetentionPolicy.RUNTIME)
```

<br/>

```
// 注解添加的位置
// ElementType.TYPE: 类、接口、注解、枚举
// ElementType.FIELD: 属性、枚举常量
// ElementType.METHOD: 方法
// ElementType.PARAMETER: 参数
@Target(ElementType.METHOD)
```
