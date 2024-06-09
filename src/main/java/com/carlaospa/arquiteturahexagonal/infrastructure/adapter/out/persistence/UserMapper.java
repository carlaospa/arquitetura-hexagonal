package com.carlaospa.arquiteturahexagonal.infrastructure.adapter.out.persistence;

import com.carlaospa.arquiteturahexagonal.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserJpaEntity toJpaEntity(User user);

    User toDomain(UserJpaEntity userJpaEntity);
}
