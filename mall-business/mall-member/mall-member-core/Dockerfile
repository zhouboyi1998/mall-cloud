FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-member
COPY ./target/mall-member.jar /mall/mall-member/mall-member.jar

EXPOSE 18086
ENTRYPOINT ["java", "-jar", "mall-member.jar", "--spring.profiles.active=docker"]
