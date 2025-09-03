<h1 align="center">🏪 mall-merchant</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-merchant -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f ./docs/docker/mall-merchant/Dockerfile -t mall-merchant:latest .
```

#### 运行

```bash
docker run -d -p 18087:18087 --name mall-merchant mall-merchant:latest
```
