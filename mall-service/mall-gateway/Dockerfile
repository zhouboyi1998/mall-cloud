FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-gateway
COPY ./target/mall-gateway.jar /mall/mall-gateway/mall-gateway.jar

EXPOSE 18071
ENTRYPOINT ["java", "-jar", "mall-gateway.jar", "--spring.profiles.active=docker"]
