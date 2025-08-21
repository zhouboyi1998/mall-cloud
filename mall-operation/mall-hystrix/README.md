<h1 align="center">🏪 mall-hystrix</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-hystrix -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f ./docs/docker/mall-hystrix/Dockerfile -t mall-hystrix:latest .
```

#### 运行

```bash
docker run -d -p 8701:8701 --name mall-hystrix mall-hystrix:latest
```
