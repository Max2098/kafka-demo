package org.demo.producer.kafka.service;

import tools.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demo.producer.kafka.entity.OutboxEvent;
import org.demo.producer.kafka.repository.OutboxEventRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class OutboxEventService {
    private final OutboxEventRepository repository;
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Transactional(transactionManager = "transactionManager")
    public <T> void save(T event, String topic, String key) {
        String json;
        try {
            json = objectMapper.writeValueAsString(event);
        } catch (Exception e) {
            throw new RuntimeException("Serialization failed for event: " + event, e);
        }

        repository.save(new OutboxEvent()
                .setKey(key)
                .setTopic(topic)
                .setPayload(json)
                .setCreatedAt(ZonedDateTime.now())
                .setSent(false)
        );
    }

    @Transactional(transactionManager = "kafkaTransactionManager")
    public void sendEvents(String topic, Class<?> classType) {
        var events = repository.findByTopicAndSentFalseOrderByCreatedAtAsc(topic);
        log.debug("Trying to send events to topic: '{}' ~ {}", topic, events);
        events.forEach(event -> {
            Object payload = objectMapper.readValue(event.getPayload(), classType);
            kafkaTemplate.send(event.getTopic(), event.getKey(), payload).whenComplete((result, ex) -> {
                if (ex != null) {
                    log.error("Error sending event: {}", event, ex);
                } else {
                    event.setSent(true);
                    repository.save(event);
                    log.info("Event was successfully sent with key: {}", event.getKey());
                }
            });
        });
    }
}
