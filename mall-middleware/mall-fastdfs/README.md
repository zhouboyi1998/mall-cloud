<h1 align="center">🏪 mall-fastdfs</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-fastdfs -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f ./docs/docker/mall-fastdfs/Dockerfile -t mall-fastdfs:latest .
```

#### 运行

```bash
docker run -d -p 18092:18092 --name mall-fastdfs mall-fastdfs:latest
```
