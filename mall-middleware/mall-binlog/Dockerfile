FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-binlog
COPY ./target/mall-binlog.jar /mall/mall-binlog/mall-binlog.jar

ENTRYPOINT ["java", "-jar", "mall-binlog.jar", "--spring.profiles.active=docker"]
