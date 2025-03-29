package com.cafe.debezium.config;

import com.cafe.debezium.property.DebeziumProperties;
import io.debezium.config.Configuration;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
@org.springframework.context.annotation.Configuration
public class DebeziumConfig {

    private final DebeziumProperties debeziumProperties;

    @Bean
    public Configuration debeziumMySQLConfig() {
        return Configuration.create()
                .with("name", debeziumProperties.getName())
                .with("connector.class", debeziumProperties.getConnectorClass())
                .with("database.hostname", debeziumProperties.getHostname())
                .with("database.port", debeziumProperties.getPort())
                .with("database.user", debeziumProperties.getUser())
                .with("database.password", debeziumProperties.getPassword())
                .with("database.server.id", debeziumProperties.getServerId())
                .with("database.server.name", debeziumProperties.getServerName())
                .with("database.include.list", debeziumProperties.getIncludeDatabases())
                .with("table.include.list", debeziumProperties.getIncludeTables())
                .with("database.history.kafka.bootstrap.servers", debeziumProperties.getKafkaBootstrapServers())
                .with("database.history.kafka.topic", debeziumProperties.getKafkaTopic())
                .build();
    }

}