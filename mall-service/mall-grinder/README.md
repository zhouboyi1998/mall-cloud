<h1 align="center">🏪 mall-grinder</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-grinder -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f ./docs/docker/mall-grinder/Dockerfile -t mall-grinder:latest .
```

#### 运行

```bash
docker run -d -p 18011:18011 --name mall-grinder mall-grinder:latest
```
