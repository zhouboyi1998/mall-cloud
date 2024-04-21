FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-apiconsumer
COPY ./mall-center/mall-apiconsumer/target/mall-apiconsumer.jar /mall/mall-apiconsumer/mall-apiconsumer.jar
COPY ./skywalking-agent /mall/skywalking-agent

EXPOSE 18062
ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-apiconsumer-service", "-Dskywalking.agent.instance_name=mall-apiconsumer-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-apiconsumer.jar", "--spring.profiles.active=docker"]
