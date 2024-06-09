package com.carlaospa.arquiteturahexagonal.infrastructure.adapter.out.persistence;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.carlaospa.arquiteturahexagonal.domain.model.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class UserMapperTest {

    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Test
    void toJpaEntity() {
        User user = new User(1L, "Carlos", "carlos@example.com");
        UserJpaEntity userJpaEntity = userMapper.toJpaEntity(user);

        assertThat(userJpaEntity.getId(), is(1L));
        assertThat(userJpaEntity.getName(), is("Carlos"));
        assertThat(userJpaEntity.getEmail(), is("carlos@example.com"));
    }

    @Test
    void toDomain() {
        UserJpaEntity userJpaEntity = new UserJpaEntity(1L, "Carlos", "carlos@example.com");
        User user = userMapper.toDomain(userJpaEntity);

        assertThat(user.getId(), is(1L));
        assertThat(user.getName(), is("Carlos"));
        assertThat(user.getEmail(), is("carlos@example.com"));
    }
}
