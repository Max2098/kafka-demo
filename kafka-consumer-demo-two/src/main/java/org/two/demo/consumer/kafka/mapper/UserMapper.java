package org.two.demo.consumer.kafka.mapper;

import org.mapstruct.Mapper;
import org.two.demo.consumer.kafka.data.User;
import org.two.demo.consumer.kafka.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity map(User source);
}
