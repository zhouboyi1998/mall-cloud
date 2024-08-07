<h1 align="center">🏪 mall-ordercenter</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-ordercenter -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f ./docs/docker/mall-ordercenter/Dockerfile -t mall-ordercenter:latest .
```

#### 运行

```bash
docker run -d -p 18061:18061 --name mall-ordercenter mall-ordercenter:latest
```
