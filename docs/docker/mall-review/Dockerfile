FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-review
COPY ./mall-business/mall-review/target/mall-review.jar /mall/mall-review/mall-review.jar
COPY ./skywalking-agent /mall/skywalking-agent

EXPOSE 18088
ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-review-service", "-Dskywalking.agent.instance_name=mall-review-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-review.jar", "--spring.profiles.active=docker"]
