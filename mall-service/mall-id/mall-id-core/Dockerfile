FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-id
COPY ./target/mall-id.jar /mall/mall-id/mall-id.jar

EXPOSE 18074
ENTRYPOINT ["java", "-jar", "mall-id.jar", "--spring.profiles.active=docker"]
