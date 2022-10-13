<h2 align="center">📔 Java</h2>

### 📑 Annotation

#### Java Global Annotation

```
@interface
```

* 标识一个类为注解

<br/>

```
@Documented
```

* 生成 `Java` 文档时, 文档只会记录有 `@Documented` 修饰的注解

<br/>

```
@Retention(RetentionPolicy.RUNTIME)
```

* 注解保留策略 (只能指定一个 `RetentionPolicy`)
* `RetentionPolicy.SOURCE`: 只保留在 `.java` 源文件中, 编译成 `.class` 字节码文件后就会删除
* `RetentionPolicy.CLASS`: 保留到 `.class` 字节码文件中, 但不加载到 `JVM` 中参与运行
* `RetentionPolicy.RUNTIME`: 一直保留到运行阶段, `JVM` 加载 `.class` 字节码文件后, 注解仍然保留

<br/>

```
@Target(ElementType.METHOD)
```

* 注解添加的位置 (可以指定多个 `ElementType`)
* `ElementType.TYPE`: 类、接口、注解、枚举
* `ElementType.FIELD`: 属性、枚举常量
* `ElementType.METHOD`: 方法
* `ElementType.PARAMETER`: 参数

<br/>

```
@Inherited
```

* 注解是否继承 (只能修饰其它注解)
* 子类会继承父类中有 `@Inherited` 修饰的注解

<br/>

```
@Override
```

* 覆盖父类中的同名方法 / 实现接口中的同名方法 (只能修饰方法)
