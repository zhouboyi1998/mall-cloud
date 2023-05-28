FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-canal
COPY ./target/mall-canal.jar /mall/mall-canal/mall-canal.jar

ENTRYPOINT ["java", "-jar", "mall-canal.jar", "--spring.profiles.active=docker"]
