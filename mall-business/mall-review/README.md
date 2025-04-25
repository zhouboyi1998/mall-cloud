<h1 align="center">🏪 mall-review</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-review -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f ./docs/docker/mall-review/Dockerfile -t mall-review:latest .
```

#### 运行

```bash
docker run -d -p 18088:18088 --name mall-review mall-review:latest
```
