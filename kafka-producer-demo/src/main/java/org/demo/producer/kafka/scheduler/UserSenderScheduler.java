package org.demo.producer.kafka.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demo.producer.kafka.data.User;
import org.demo.producer.kafka.service.OutboxEventService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserSenderScheduler {

    private final OutboxEventService outboxEventService;

    @Value("${demo.kafka.topics.user-topic}")
    private String userTopic;

    @ConditionalOnProperty(name = "demo.kafka.scheduler.save.enabled", havingValue = "true")
    @Scheduled(fixedRateString = "${demo.kafka.scheduler.save.fixed-rate:10000}")
    public void save() {
        var user = new User()
                .setUniqueKey(UUID.randomUUID().toString())
                .setName(UUID.randomUUID().toString())
                .setPhone(UUID.randomUUID().toString())
                .setBalance(BigDecimal.valueOf(Math.random() * 1000))
                .setBirthday(LocalDate.now())
                .setCreateAt(ZonedDateTime.now());
        log.debug("Save user for sending to topic: {} ~ {}", userTopic, user);
        outboxEventService.save(user, userTopic, user.getUniqueKey());
    }

    @ConditionalOnProperty(name = "demo.kafka.scheduler.send.enabled", havingValue = "true")
    @Scheduled(fixedRateString = "${demo.kafka.scheduler.send.fixed-rate:30000}")
    public void send() {
        log.debug("Start scheduler for sending events");
        outboxEventService.sendEvents(userTopic, User.class);
    }
}