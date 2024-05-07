<h1 align="center">🏪 mall-minio</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-minio -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f docs/docker/mall-minio/Dockerfile -t mall-minio .
```

#### 运行

```bash
docker run -d -p 18091:18091 --name mall-minio mall-minio
```
