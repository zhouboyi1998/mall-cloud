FROM openjdk:8-jre
LABEL maintainer="zhouboyi<1144188685@qq.com>"

WORKDIR /mall/mall-member
COPY ./mall-business/mall-member/mall-member-core/target/mall-member.jar /mall/mall-member/mall-member.jar
COPY ./skywalking-agent /mall/skywalking-agent

EXPOSE 18086
ENTRYPOINT ["java", "-javaagent:/mall/skywalking-agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=mall-member-service", "-Dskywalking.agent.instance_name=mall-member-instance", "-Dskywalking.collector.backend_service=host.docker.internal:11800", "-jar", "mall-member.jar", "--spring.profiles.active=docker"]
