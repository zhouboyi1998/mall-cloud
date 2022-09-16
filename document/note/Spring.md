<h2 align="center">📔 Spring</h2>

### 📑 AOP

#### 术语

* `Aspect (切面)`
* `JoinPoint (连接点)`
* `Pointcut (切入点)`
* `Introduction (引入)`
* `Target Object (目标对象)`
* `AOP Proxy (AOP 代理)`
* `Weaving (织入)`
* `Advice (通知)`

<br/>

#### 通知类型

* `Before Advice (前置通知)`
* `Around Advice (环绕通知)`
* `After Returning Advice (后置通知)`
* `After Throwing Advice (异常通知)`
* `After Advice (最终通知)`

<br/>

#### 通知顺序

* 在 `Spring 5.3.8` 版本之后，AOP 通知的执行顺序发生了改变

<br/>

* 没有抛出异常时的代码执行顺序如下：
    1. `Around Advice` 进入连接点之前的代码
    2. `Before Advice`
    3. `After Returning Advice`
    4. `After Advice`
    5. `Around Advice` 进入连接点之后的代码

<br/>

* 有抛出异常时的代码执行顺序如下：
    1. `Around Advice` 进入连接点之前的代码
    2. `Before Advice`
    3. `After Throwing Advice`
    4. `After Advice`
    5. `Around Advice` 进入连接点之后的代码
