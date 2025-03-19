<h1 align="center">🏪 mall-order</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-order -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f ./docs/docker/mall-order/Dockerfile -t mall-order:latest .
```

#### 运行

```bash
docker run -d -p 18082:18082 --name mall-order mall-order:latest
```
