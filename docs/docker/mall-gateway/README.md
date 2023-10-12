<h1 align="center">🏪 mall-gateway</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-gateway -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f docs/docker/mall-gateway/Dockerfile -t mall-gateway .
```

#### 运行

```bash
docker run -d -p 18071:18071 --name mall-gateway mall-gateway
```
