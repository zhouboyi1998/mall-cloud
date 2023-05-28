FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-order
COPY ./target/mall-order.jar /mall/mall-order/mall-order.jar

EXPOSE 18082
ENTRYPOINT ["java", "-jar", "mall-order.jar", "--spring.profiles.active=docker"]
