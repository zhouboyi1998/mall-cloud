<h1 align="center">🏪 mall-id</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-id -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f docs/docker/mall-id/Dockerfile -t mall-id .
```

#### 运行

```bash
docker run -d -p 18074:18074 --name mall-id mall-id
```
