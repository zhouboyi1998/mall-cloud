FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-goods
COPY ./mall-business/mall-goods/mall-goods-core/target/mall-goods.jar /mall/mall-goods/mall-goods.jar
COPY ./skywalking-agent /mall/skywalking-agent

EXPOSE 18081
ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-goods-service", "-Dskywalking.agent.instance_name=mall-goods-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-goods.jar", "--spring.profiles.active=docker"]
