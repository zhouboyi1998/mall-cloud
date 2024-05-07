<h1 align="center">🏪 mall-user</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-user -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f docs/docker/mall-user/Dockerfile -t mall-user .
```

#### 运行

```bash
docker run -d -p 18073:18073 --name mall-user mall-user
```
