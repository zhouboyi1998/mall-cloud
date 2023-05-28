FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-storage
COPY ./target/mall-storage.jar /mall/mall-storage/mall-storage.jar

EXPOSE 18083
ENTRYPOINT ["java", "-jar", "mall-storage.jar", "--spring.profiles.active=docker"]
