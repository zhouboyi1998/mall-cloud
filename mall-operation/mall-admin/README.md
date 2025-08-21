<h1 align="center">🏪 mall-admin</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-admin -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f ./docs/docker/mall-admin/Dockerfile -t mall-admin:latest .
```

#### 运行

```bash
docker run -d -p 18075:18075 --name mall-admin mall-admin:latest
```
