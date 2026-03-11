package org.demo.producer.kafka.repository;

import org.demo.producer.kafka.entity.OutboxEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OutboxEventRepository extends JpaRepository<OutboxEvent, String> {
    Set<OutboxEvent> findByTopicAndSentFalseOrderByCreatedAtAsc(String topic);
}
