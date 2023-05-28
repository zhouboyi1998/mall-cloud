FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-minio
COPY ./target/mall-minio.jar /mall/mall-minio/mall-minio.jar

EXPOSE 18091
ENTRYPOINT ["java", "-jar", "mall-minio.jar", "--spring.profiles.active=docker"]
