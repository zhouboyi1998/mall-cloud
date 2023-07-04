FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-admin
COPY ./target/mall-admin.jar /mall/mall-admin/mall-admin.jar

EXPOSE 18075
ENTRYPOINT ["java", "-jar", "mall-admin.jar", "--spring.profiles.active=docker"]
