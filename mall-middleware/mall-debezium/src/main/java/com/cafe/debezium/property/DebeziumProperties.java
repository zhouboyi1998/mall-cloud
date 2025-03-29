package com.cafe.debezium.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@ConfigurationProperties(prefix = "debezium.mysql")
@Getter
@Setter
public class DebeziumProperties {
    private String connectorClass;
    private String name;
    private String hostname;
    private String port;
    private String user;
    private String password;
    private String serverId;
    private String serverName;
    private String includeDatabases;
    private String includeTables;
    private String kafkaBootstrapServers;
    private String kafkaTopic;

}