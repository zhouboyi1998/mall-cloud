FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-eureka
COPY ./mall-service/mall-eureka/target/mall-eureka.jar /mall/mall-eureka/mall-eureka.jar
COPY ./skywalking-agent /mall/skywalking-agent

EXPOSE 8761
ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-eureka-service", "-Dskywalking.agent.instance_name=mall-eureka-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-eureka.jar", "--spring.profiles.active=docker"]
