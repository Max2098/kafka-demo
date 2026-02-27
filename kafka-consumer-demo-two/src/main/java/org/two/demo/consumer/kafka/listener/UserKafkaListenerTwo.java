package org.two.demo.consumer.kafka.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.two.demo.consumer.kafka.data.User;
import org.two.demo.consumer.kafka.mapper.UserMapper;
import org.two.demo.consumer.kafka.service.UserService;


@Slf4j
@Component
@RequiredArgsConstructor
public class UserKafkaListenerTwo {
    private final UserMapper mapper;
    private final UserService userService;

    private static final String topic = "${demo.kafka.topics.user-topic}";
    private static final String groupId = "${demo.kafka.group-id.user-group-id}";

    @Transactional
    @KafkaListener(
            topics = topic, groupId = groupId,
            properties = {"spring.json.value.default.type=org.two.demo.consumer.kafka.data.User"}
    )
    void listenerWithMessageConverter(User user) {
        log.debug("Received message from producer: [{}]", user);
        userService.save(mapper.map(user));
    }
}