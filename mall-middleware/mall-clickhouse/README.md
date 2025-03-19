<h1 align="center">🏪 mall-clickhouse</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-clickhouse -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f ./docs/docker/mall-clickhouse/Dockerfile -t mall-clickhouse:latest .
```

#### 运行

```bash
docker run -d -p 18096:18096 --name mall-clickhouse mall-clickhouse:latest
```
