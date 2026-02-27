package org.two.demo.consumer.kafka.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.two.demo.consumer.kafka.entity.UserEntity;
import org.two.demo.consumer.kafka.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    @Transactional
    public void save(UserEntity user) {
        var entity = repository.save(user);
        log.debug("Saved user entity: {}", entity);
    }
}
