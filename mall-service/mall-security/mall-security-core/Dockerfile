FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-security
COPY ./target/mall-security.jar /mall/mall-security/mall-security.jar

EXPOSE 18072
ENTRYPOINT ["java", "-jar", "mall-security.jar", "--spring.profiles.active=docker"]
