package org.two.demo.consumer.kafka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.two.demo.consumer.kafka.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
