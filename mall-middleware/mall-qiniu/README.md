<h1 align="center">🏪 mall-qiniu</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-qiniu -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f ./docs/docker/mall-qiniu/Dockerfile -t mall-qiniu:latest .
```

#### 运行

```bash
docker run -d -p 18095:18095 --name mall-qiniu mall-qiniu:latest
```
