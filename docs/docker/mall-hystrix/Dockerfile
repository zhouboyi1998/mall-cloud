FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-hystrix
COPY ./mall-service/mall-hystrix/target/mall-hystrix.jar /mall/mall-hystrix/mall-hystrix.jar
COPY ./skywalking-agent /mall/skywalking-agent

EXPOSE 8701
ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-hystrix-service", "-Dskywalking.agent.instance_name=mall-hystrix-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-hystrix.jar", "--spring.profiles.active=docker"]
