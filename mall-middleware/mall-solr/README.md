<h1 align="center">🏪 mall-solr</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-solr -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f ./docs/docker/mall-solr/Dockerfile -t mall-solr:latest .
```

#### 运行

```bash
docker run -d -p 18094:18094 --name mall-solr mall-solr:latest
```
