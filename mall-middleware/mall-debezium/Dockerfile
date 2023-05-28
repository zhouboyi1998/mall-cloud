FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-debezium
COPY ./target/mall-debezium.jar /mall/mall-debezium/mall-debezium.jar

ENTRYPOINT ["java", "-jar", "mall-debezium.jar", "--spring.profiles.active=docker"]
