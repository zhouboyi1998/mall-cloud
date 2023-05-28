FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-goods
COPY ./target/mall-goods.jar /mall/mall-goods/mall-goods.jar

EXPOSE 18081
ENTRYPOINT ["java", "-jar", "mall-goods.jar", "--spring.profiles.active=docker"]
