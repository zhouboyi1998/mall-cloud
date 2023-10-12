<h1 align="center">🏪 mall-goods</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-goods-core -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f docs/docker/mall-goods/Dockerfile -t mall-goods .
```

#### 运行

```bash
docker run -d -p 18081:18081 --name mall-goods mall-goods
```
