<h1 align="center">🏪 mall-captcha</h1>

### 📦 Maven

#### 打包

```bash
# 项目根目录下运行
mvn package -pl :mall-captcha -am
```

### 🐳 Docker

#### 构建

```bash
# 项目根目录下运行
docker build -f ./docs/docker/mall-captcha/Dockerfile -t mall-captcha:latest .
```

#### 运行

```bash
docker run -d -p 18077:18077 --name mall-captcha mall-captcha:latest
```
