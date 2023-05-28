FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-merchant
COPY ./target/mall-merchant.jar /mall/mall-merchant/mall-merchant.jar

EXPOSE 18087
ENTRYPOINT ["java", "-jar", "mall-merchant.jar", "--spring.profiles.active=docker"]
