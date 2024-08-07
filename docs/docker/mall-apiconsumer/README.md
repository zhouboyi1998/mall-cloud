<h1 align="center">🏪 mall-apiconsumer</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-apiconsumer -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f docs/docker/mall-apiconsumer/Dockerfile -t mall-apiconsumer .
```

#### 运行

```bash
docker run -d -p 18062:18062 --name mall-apiconsumer mall-apiconsumer
```
