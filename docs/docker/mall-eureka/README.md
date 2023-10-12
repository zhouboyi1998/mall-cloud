<h1 align="center">🏪 mall-eureka</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-eureka -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f docs/docker/mall-eureka/Dockerfile -t mall-eureka .
```

#### 运行

```bash
docker run -d -p 18761:18761 --name mall-eureka mall-eureka
```
