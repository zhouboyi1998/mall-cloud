<h1 align="center">🏪 mall-elasticsearch</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-elasticsearch -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f docs/docker/mall-elasticsearch/Dockerfile -t mall-elasticsearch .
```

#### 运行

```bash
docker run -d -p 18093:18093 --name mall-elasticsearch mall-elasticsearch
```
