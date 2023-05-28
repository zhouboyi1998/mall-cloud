FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-eureka
COPY ./target/mall-eureka.jar /mall/mall-eureka/mall-eureka.jar

EXPOSE 18761
ENTRYPOINT ["java", "-jar", "mall-eureka.jar", "--spring.profiles.active=docker"]
