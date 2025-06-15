<h1 align="center">🏪 mall-opensearch</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-opensearch -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f ./docs/docker/mall-opensearch/Dockerfile -t mall-opensearch:latest .
```

#### 运行

```bash
docker run -d -p 18098:18098 --name mall-opensearch mall-opensearch:latest
```
