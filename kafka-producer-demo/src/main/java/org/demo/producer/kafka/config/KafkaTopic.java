package org.demo.producer.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {

    @Value("${demo.kafka.topics.user-topic:user-topic}")
    private String userTopic;

    @Value("${demo.kafka.topics.replicas:3}")
    private int replicas;

    @Value("${demo.kafka.topics.partitions:2}")
    private int partitions;

    @Bean
    public NewTopic userTopic() {
        return TopicBuilder
                .name(userTopic)
                .replicas(replicas)
                .partitions(partitions)
                .build();
    }
}