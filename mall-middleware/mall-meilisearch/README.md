<h1 align="center">🏪 mall-meilisearch</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-meilisearch -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f ./docs/docker/mall-meilisearch/Dockerfile -t mall-meilisearch:latest .
```

#### 运行

```bash
docker run -d -p 18097:18097 --name mall-meilisearch mall-meilisearch:latest
```
