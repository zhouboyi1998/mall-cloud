FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-merchant
COPY ./mall-business/mall-merchant/target/mall-merchant.jar /mall/mall-merchant/mall-merchant.jar
COPY ./skywalking-agent /mall/skywalking-agent

EXPOSE 18087
ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-merchant-service", "-Dskywalking.agent.instance_name=mall-merchant-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-merchant.jar", "--spring.profiles.active=docker"]
