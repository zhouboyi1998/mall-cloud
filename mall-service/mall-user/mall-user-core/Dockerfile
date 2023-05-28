FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-user
COPY ./target/mall-user.jar /mall/mall-user/mall-user.jar

EXPOSE 18073
ENTRYPOINT ["java", "-jar", "mall-user.jar", "--spring.profiles.active=docker"]
