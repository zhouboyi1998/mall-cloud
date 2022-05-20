<h2 align="center">📔 RabbitMQ</h2>

### 📑 基础知识

* 概念

```
Exchange (交换机)
Queue (队列)
RoutingKey (路由键)
Binding (绑定)
Connection (连接)
Channel (管道)
Admin (用户)
```

* `Exchange` 和 `Queue` 可以通过 `RoutingKey` 进行 `Binding`
* `Binding` 关系是 `多对多` 的
    * 一个 `Exchange` 可以 `Binding` 多个 `Queue`
    * 一个 `Queue` 也可以 `Binding` 多个 `Exchange`
* 消息可以直接发送到 `Queue` 中
* 也可以发送到 `Exchange` 中，让 `Exchange` 根据 `RoutingKey` 转发给 `Binding` 的 `Queue`

---

* RabbitMQ 六种模式

```
Simple (简单模式)
Work Queue (工作队列模式, 负载均衡)
Publis / Subscribe (发布/订阅模式)
Routing (路由模式)
Topics (主题模式)
RPC (RPC 模式, 一种非 MQ 的模式)
```

---

* Exchange 四种类型

```
Fanout (扇出类型)
    |-- 对应"发布/订阅模式"
Direct (直接类型)
    |-- 对应"路由模式"
Topic (主题类型)
    |-- 对应"主题模式"
Header (首部类型)
```

