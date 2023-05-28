FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-system
COPY ./target/mall-system.jar /mall/mall-system/mall-system.jar

EXPOSE 18084
ENTRYPOINT ["java", "-jar", "mall-system.jar", "--spring.profiles.active=docker"]
