<h2 align="center">📔 MinIO</h2>

### 🐳 Docker

* 拉取 `MinIO` 镜像

```bash
docker pull minio/minio:RELEASE.2022-05-26T05-48-41Z
```

* 运行 `MinIO` 容器
    * `-e "MINIO_ROOT_USER=administrator"` 管理员用户名
    * `-e "MINIO_ROOT_PASSWORD=123456789"` 管理员密码
    * `-v D:\Software\minio\data:/data` 数据存储位置磁盘映射
    * `-v D:\Software\minio\config:/root/.minio` 配置文件存储位置磁盘映射
    * `minio/minio:RELEASE.2022-05-26T05-48-41Z` 镜像名
    * `--console-address ":9001"` 开放控制台需要使用的 `9001` 端口

```bash
docker run -d -p 9000:9000 -p 9001:9001 --name minio -e "MINIO_ROOT_USER=administrator" -e "MINIO_ROOT_PASSWORD=123456789" -v D:\Software\minio\data:/data -v D:\Software\minio\config:/root/.minio minio/minio:RELEASE.2022-05-26T05-48-41Z server /data --console-address ":9001"
```
