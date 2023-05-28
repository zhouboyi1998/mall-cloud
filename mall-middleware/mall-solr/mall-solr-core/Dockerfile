FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-solr
COPY ./target/mall-solr.jar /mall/mall-solr/mall-solr.jar

EXPOSE 18094
ENTRYPOINT ["java", "-jar", "mall-solr.jar", "--spring.profiles.active=docker"]
