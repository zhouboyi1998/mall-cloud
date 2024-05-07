<h1 align="center">🏪 mall-manager</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-manager -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f docs/docker/mall-manager/Dockerfile -t mall-manager .
```

#### 运行

```bash
docker run -d -p 18085:18085 --name mall-manager mall-manager
```
