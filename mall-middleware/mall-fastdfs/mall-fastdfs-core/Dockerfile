FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-fastdfs
COPY ./target/mall-fastdfs.jar /mall/mall-fastdfs/mall-fastdfs.jar

EXPOSE 18092
ENTRYPOINT ["java", "-jar", "mall-fastdfs.jar", "--spring.profiles.active=docker"]
