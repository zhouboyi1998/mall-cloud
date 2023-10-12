FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-order
COPY ./mall-business/mall-order/mall-order-core/target/mall-order.jar /mall/mall-order/mall-order.jar
COPY ./skywalking-agent /mall/skywalking-agent

EXPOSE 18082
ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-order-service", "-Dskywalking.agent.instance_name=mall-order-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-order.jar", "--spring.profiles.active=docker"]
