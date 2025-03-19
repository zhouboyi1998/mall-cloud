<h1 align="center">🏪 mall-debezium</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-debezium -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f ./docs/docker/mall-debezium/Dockerfile -t mall-debezium:latest .
```

#### 运行

```bash
docker run -d --name mall-debezium mall-debezium:latest
```
