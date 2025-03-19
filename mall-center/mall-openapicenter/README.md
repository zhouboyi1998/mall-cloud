<h1 align="center">🏪 mall-openapicenter</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-openapicenter -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f ./docs/docker/mall-openapicenter/Dockerfile -t mall-openapicenter:latest .
```

#### 运行

```bash
docker run -d -p 18062:18062 --name mall-openapicenter mall-openapicenter:latest
```
