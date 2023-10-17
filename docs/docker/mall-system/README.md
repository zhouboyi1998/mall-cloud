<h1 align="center">🏪 mall-system</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-system -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f docs/docker/mall-system/Dockerfile -t mall-system .
```

#### 运行

```bash
docker run -d -p 18084:18084 --name mall-system mall-system
```
