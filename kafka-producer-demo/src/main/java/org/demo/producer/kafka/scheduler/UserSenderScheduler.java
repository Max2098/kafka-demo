package org.demo.producer.kafka.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demo.producer.kafka.data.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
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

    private final KafkaTemplate<String, Object> userKafkaTemplate;

    @Value("${demo.kafka.topics.user-topic}")
    private String userTopic;

    @Scheduled(fixedRateString = "30000")
    public void send() {
        var user = new User()
                .setName(UUID.randomUUID().toString())
                .setPhone(UUID.randomUUID().toString())
                .setBalance(BigDecimal.valueOf(Math.random() * 1000))
                .setBirthday(LocalDate.now())
                .setCreateAt(ZonedDateTime.now());
        log.debug("Send user to topic: {} ~ {}", userTopic, user);
        userKafkaTemplate.send(userTopic, user.getName(), user);
    }
}