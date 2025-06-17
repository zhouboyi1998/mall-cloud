<h1 align="center">🏪 mall-zuul</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-zuul -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f ./docs/docker/mall-zuul/Dockerfile -t mall-zuul:latest .
```

#### 运行

```bash
docker run -d -p 8040:8040 --name mall-zuul mall-zuul:latest
```
