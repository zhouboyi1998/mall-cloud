<h1 align="center">🏪 mall-swagger</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-swagger -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f ./docs/docker/mall-swagger/Dockerfile -t mall-swagger:latest .
```

#### 运行

```bash
docker run -d -p 18076:18076 --name mall-swagger mall-swagger:latest
```
