<h1 align="center">🏪 mall-canal</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-canal -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f docs/docker/mall-canal/Dockerfile -t mall-canal .
```

#### 运行

```bash
docker run -d --name mall-canal mall-canal
```
