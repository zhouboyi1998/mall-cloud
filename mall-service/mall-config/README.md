<h1 align="center">🏪 mall-config</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-config -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f docs/docker/mall-config/Dockerfile -t mall-config .
```

#### 运行

```bash
docker run -d -p 8888:8888 --name mall-config mall-config
```
