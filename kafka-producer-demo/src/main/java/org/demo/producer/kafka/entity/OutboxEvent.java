package org.demo.producer.kafka.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@ToString
@Table(name = "outbox_event")
public class OutboxEvent {

    @Id
    @Column(name = "key")
    private String key;

    @Column(name = "topic", nullable = false)
    private String topic;

    @Column(name = "payload", nullable = false)
    private String payload;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "sent")
    private Boolean sent;
}
