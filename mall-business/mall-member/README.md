<h1 align="center">🏪 mall-member</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-member -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f ./docs/docker/mall-member/Dockerfile -t mall-member:latest .
```

#### 运行

```bash
docker run -d -p 18086:18086 --name mall-member mall-member:latest
```
