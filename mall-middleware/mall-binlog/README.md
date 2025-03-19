<h1 align="center">🏪 mall-binlog</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-binlog -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f ./docs/docker/mall-binlog/Dockerfile -t mall-binlog:latest .
```

#### 运行

```bash
docker run -d --name mall-binlog mall-binlog:latest
```
