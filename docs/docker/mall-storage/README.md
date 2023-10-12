<h1 align="center">🏪 mall-storage</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-storage-core -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f docs/docker/mall-storage/Dockerfile -t mall-storage .
```

#### 运行

```bash
docker run -d -p 18083:18083 --name mall-storage mall-storage
```
