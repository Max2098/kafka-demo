package org.one.demo.consumer.kafka.repository;

import org.one.demo.consumer.kafka.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUniqueKey(String key);
}
