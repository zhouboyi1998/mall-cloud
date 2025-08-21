<h1 align="center">🏪 mall-turbine</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-turbine -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f ./docs/docker/mall-turbine/Dockerfile -t mall-turbine:latest .
```

#### 运行

```bash
docker run -d -p 8702:8702 --name mall-turbine mall-turbine:latest
```
