FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-elasticsearch
COPY ./target/mall-elasticsearch.jar /mall/mall-elasticsearch/mall-elasticsearch.jar

EXPOSE 18093
ENTRYPOINT ["java", "-jar", "mall-elasticsearch.jar", "--spring.profiles.active=docker"]
