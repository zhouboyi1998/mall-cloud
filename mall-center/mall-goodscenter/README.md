<h1 align="center">🏪 mall-goodscenter</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-goodscenter -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f ./docs/docker/mall-goodscenter/Dockerfile -t mall-goodscenter:latest .
```

#### 运行

```bash
docker run -d -p 18063:18063 --name mall-goodscenter mall-goodscenter:latest
```
