<h1 align="center">🏪 mall-foundation</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-foundation -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f docs/docker/mall-foundation/Dockerfile -t mall-foundation .
```

#### 运行

```bash
docker run -d -p 18084:18084 --name mall-foundation mall-foundation
```
