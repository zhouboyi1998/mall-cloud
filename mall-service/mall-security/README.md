<h1 align="center">🏪 mall-security</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-security -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f docs/docker/mall-security/Dockerfile -t mall-security .
```

#### 运行

```bash
docker run -d -p 18072:18072 --name mall-security mall-security
```
