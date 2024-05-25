<h2 align="center">📔 Kubernetes</h2>

### 📦 Ubuntu 安装

* 更新软件包索引

```shell
sudo apt-get update
```

* 更新软件包版本

```shell
sudo apt-get upgrade
```

* 禁用交换分区

```shell
sudo swapoff -a
```

* 修改配置文件永久禁用交换分区

```shell
sudo vim /etc/fstab
```

* 注释掉 `/swapfile` 开头那一行


* 安装 `bridge-utils`

```shell
sudo apt-get install bridge-utils
```

* 开机自动挂载 `br_netfilter` 模块到内核

```shell
modprobe br_netfilter
```

* 检查 `br_netfilter` 模块是否成功挂载

```shell
lsmod | grep br_netfilter
```

* 安装 `HTTPS` 相关的软件包

```shell
sudo apt-get install apt-transport-https ca-certificates curl software-properties-common
```

* 从阿里云下载 `Kubernetes APT Key`

```shell
sudo curl -s https://mirrors.aliyun.com/kubernetes/apt/doc/apt-key.gpg | sudo apt-key add -
```

* 修改 `/etc/apt/sources.list.d` 目录权限

```shell
sudo chmod 777 /etc/apt/sources.list.d
```

* 配置 `Kubernetes` 的阿里云源

```shell
sudo echo 'deb https://mirrors.aliyun.com/kubernetes/apt/ kubernetes-xenial main' >> /etc/apt/sources.list.d/kubernetes.list
```

* 再次更新软件包索引

```shell
sudo apt-get update
```

* 安装 `Kubelet`、`Kubeadm`、`Kubectl`

```shell
sudo apt-get install -y kubelet kubeadm kubectl
```

* 查看是否安装成功

```shell
kubectl version
```

* 锁定 `Kubelet`、`Kubeadm`、`Kubectl` 版本

```shell
sudo apt-mark hold kubelet kubeadm kubectl
```

* 查看 `Kubernetes` 节点列表

```shell
kubectl get nodes
```

---

### 🚢 Kubernetes YAML 文件

#### Pod 模板 `mall-app-pod.yaml`

```yaml
apiVersion: v1
kind: Pod
metadata:
  namespace: mall
  name: mall-app-pod
  labels:
    app: mall-app
spec:
  restartPolicy: Always
  containers:
    - name: mall-app-container
      image: 192.168.159.128:5000/mall/mall-app:latest
      imagePullPolicy: Always
      ports:
        - containerPort: 8080
          protocol: TCP
      resources:
        requests:
          cpu: 200m
          memory: 256Mi
        limits:
          cpu: 1
          memory: 1Gi
```

###### 配置项

* `apiVersion`：当前配置文件使用的 `API` 版本


* `kind`：资源类型


* `metadata`：资源的元数据
    * `namespace`：命名空间
    * `name`：资源名称（资源名称是必须设置的元数据项）
    * `label`：标签（标签中的 `key` 和 `value` 都是自定义的）


* `spec`：规格配置
    * `restartPolicy`：重启策略
        * `Always (默认)`：`Pod` 终止都会自动重新启动
        * `OnFailure`：只有当 `Pod` 非零终止时才会自动重新启动
        * `Never`：`Pod` 终止不会自动重新启动
    * `containers`：`Pod` 中包含的容器列表
        * `image`：启动容器所用的镜像
        * `imagePullPolicy`：镜像拉取策略
            * `IfNotPresent (默认)`：本地没有该镜像，才会去镜像仓库拉取镜像
            * `Always`：每次构建镜像都会去镜像仓库拉取镜像
            * `Never`：只会使用本地镜像，不会去镜像仓库拉取镜像
    * `ports`：容器暴露的端口列表
    * `resources`：资源配置
        * `requests`：请求资源配置（最低需要分配多少资源）
            * `cpu`：`CPU` 资源
                * `200m`：最低分配 `200` 毫核 `CPU`（`1` 个 `CPU` 内核被 `Kubernetes` 划分为 `1000` 毫核）
            * `memory`：内存资源
                * `256Mi`：最低分配 `256MB` 内存
        * `limits`：限制资源配置（最高可以获取多少资源）
            * `cpu`：`CPU` 资源
                * `1`：最高分配 `1` 个 `CPU` 内核
            * `memory`：内存资源
                * `1Gi`：最高分配 `1GB` 内存

#### ReplicaSet 模板 `mall-app-replicaset.yaml`

```yaml
apiVersion: apps/v1
kind: ReplicaSet
metadata:
  namespace: mall
  name: mall-app-replicaset
  labels:
    name: mall-app-replicaset
spec:
  replicas: 3
  selector:
    matchLabels:
      app: mall-app
  template:
    metadata:
      namespace: mall
      name: mall-app-pod
      labels:
        app: mall-app
    spec:
      restartPolicy: Always
      containers:
        - name: mall-app-container
          image: 192.168.159.128:5000/mall/mall-app:latest
          imagePullPolicy: Always
          ports:
            - containerPort: 8080
              protocol: TCP
          resources:
            requests:
              cpu: 200m
              memory: 256Mi
            limits:
              cpu: 1
              memory: 1Gi
```

###### 配置项

* `spec`：
    * `replicas`：启动的 `Pod` 的数量（默认数量为 `1`）
    * `selector`：匹配规则
        * `matchLabels`：使用标签匹配 `Pod`（例子中匹配拥有 `app: mall-app` 标签的 `Pod`）
    * `template`：`ReplicaSet` 需要启动的 `Pod` 的模板

#### Deployment 模板 `mall-app-deployment.yaml`

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  namespace: mall
  name: mall-app-deployment
  labels:
    name: mall-app-deployment
spec:
  replicas: 3
  selector:
    matchLabels:
      app: mall-app
  template:
    metadata:
      namespace: mall
      name: mall-app-pod
      labels:
        app: mall-app
    spec:
      restartPolicy: Always
      containers:
        - name: mall-app-container
          image: 192.168.159.128:5000/mall/mall-app:latest
          imagePullPolicy: Always
          ports:
            - containerPort: 8080
              protocol: TCP
          resources:
            requests:
              cpu: 200m
              memory: 256Mi
            limits:
              cpu: 1
              memory: 1Gi
```

#### Service 模板 `mall-app-service.yaml`

```yaml
apiVersion: v1
kind: Service
metadata:
  namespace: mall
  name: mall-app-service
spec:
  type: NodePort
  selector:
    app: mall-app
  ports:
    - nodePort: 30080
      port: 8080
      targetPort: 8080
      protocol: TCP
```

###### 配置项

* `spec`：规格配置
    * `type`：`Service` 的类型
    * `selector`：使用标签匹配 `Pod`（例子中匹配拥有 `app: mall-app` 标签的 `Pod`）
    * `ports`：`Service` 暴露的端口列表
        * `nodePort`：外部请求访问该 `Service` 使用的端口（`nodePort` 限制范围：`30000 - 32767`）
        * `port`：外部请求使用 `nodePort` 配置的端口访问该 `Service` 后，交给 `Service` 的哪个端口来处理请求
        * `targetPort`：请求交给 `Service` 的指定端口处理后，`Service` 转发给 `Pod` 的哪个端口来处理请求
        * `protocol`：端口协议（默认是 `TCP`）

---

### 🛞 Kubernetes 命令

#### Pod

* 使用 `YAML` 文件创建 `Pod`

```shell
kubectl create -f mall-app-pod.yaml
```

* 查看 `Pod` 列表

```shell
kubectl get pod
```

* 删除 `Pod`

```shell
kubectl delete pod mall-app-pod
```

#### ReplicaSet

* 使用 `YAML` 文件创建 `ReplicaSet`

```shell
kubectl create -f mall-app-replicaset.yaml
```

查看 `ReplicaSet` 列表

```shell
kubectl get replicaset
```

```shell
kubectl get rs
```

* 更新 `ReplicaSet` 配置
    * 修改 `YAML` 文件后，执行以下命令更新 `ReplicaSet` 配置

```shell
kubecet replace -f mall-app-replicaset.yaml
```

* 在不修改 `YAML` 文件的情况下更新 `ReplicaSet` 配置
    * 例如执行以下命令临时将 `Pod` 数量扩缩容成 `4` 个

```shell
kubectl scale --replicas=4 -f mall-app-replicaset.yaml
```

* 删除 `ReplicaSet`

```shell
kubectl delete rs mall-app-replicaset
```

#### Deployment

* 使用 `YAML` 文件创建 `Deployment`

```shell
kubectl create -f mall-app-deployment.yaml
```

* 查看 `Deployment` 列表

```shell
kubectl get deployment
```

* 更新 `Deployment` 配置
    * 修改 `YAML` 文件后，执行以下命令更新 `Deployment` 配置

```shell
kubectl apply -f mall-app-deployment.yaml
```

* 删除 `Deployment`

```shell
kubectl delete deployment mall-app-deployment
```

#### Service

* 使用 `YAML` 文件创建 `Service`

```shell
kubectl create -f mall-app-service.yaml
```

查看 `Service` 列表

```shell
kubectl get service
```

删除 `Service`

```shell
kubectl delete service mall-app-service
```

#### Namespace

* 创建 `Namespace`

```shell
kubectl create namespace mall
```

* 查看 `Namespace` 列表

```shell
kubectl get namespace
```

* 删除 `Namespace`

```shell
kubectl delete namespace mall
```

* 操作指定的 `Namespace`
    * 使用 `-n` 或 `--namespace` 指定需要操作的命名空间
    * 不指定时操作的是默认命名空间 `default`

```shell
kubectl get deployment -n mall
```

```shell
kubectl delete deployment mall-app-deployment -n mall
```
