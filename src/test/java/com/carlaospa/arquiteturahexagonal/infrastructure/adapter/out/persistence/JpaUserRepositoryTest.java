package com.carlaospa.arquiteturahexagonal.infrastructure.adapter.out.persistence;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import com.carlaospa.arquiteturahexagonal.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class JpaUserRepositoryTest {

    @Mock
    private SpringDataJpaUserRepository springDataJpaUserRepository;

    @Mock
    private UserMapper userMapper;

    private JpaUserRepository jpaUserRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jpaUserRepository = new JpaUserRepository(springDataJpaUserRepository, userMapper);
    }

    @Test
    void save() {
        User user = new User(null, "Carlos", "carlos@example.com");
        UserJpaEntity userJpaEntity = new UserJpaEntity("Carlos", "carlos@example.com");

        when(userMapper.toJpaEntity(user)).thenReturn(userJpaEntity);
        when(springDataJpaUserRepository.save(userJpaEntity)).thenReturn(new UserJpaEntity(1L, "Carlos", "carlos@example.com"));
        when(userMapper.toDomain(any())).thenReturn(new User(1L, "Carlos", "carlos@example.com"));

        User savedUser = jpaUserRepository.save(user);

        assertThat(savedUser.getId(), is(1L));
        assertThat(savedUser.getName(), is("Carlos"));
        assertThat(savedUser.getEmail(), is("carlos@example.com"));
    }

    @Test
    void deleteById() {
        doNothing().when(springDataJpaUserRepository).deleteById(1L);

        jpaUserRepository.deleteById(1L);

        verify(springDataJpaUserRepository, times(1)).deleteById(1L);
    }

    @Test
    void findById() {
        UserJpaEntity userJpaEntity = new UserJpaEntity(1L, "Carlos", "carlos@example.com");
        when(springDataJpaUserRepository.findById(1L)).thenReturn(Optional.of(userJpaEntity));
        when(userMapper.toDomain(userJpaEntity)).thenReturn(new User(1L, "Carlos", "carlos@example.com"));

        Optional<User> foundUser = jpaUserRepository.findById(1L);

        assertThat(foundUser.isPresent(), is(true));
        assertThat(foundUser.get().getId(), is(1L));
        assertThat(foundUser.get().getName(), is("Carlos"));
        assertThat(foundUser.get().getEmail(), is("carlos@example.com"));
    }

    @Test
    void findAll() {
        UserJpaEntity userJpaEntity1 = new UserJpaEntity(1L, "Carlos", "carlos@example.com");
        UserJpaEntity userJpaEntity2 = new UserJpaEntity(2L, "Jane Doe", "jane.doe@example.com");
        when(springDataJpaUserRepository.findAll()).thenReturn(Arrays.asList(userJpaEntity1, userJpaEntity2));
        when(userMapper.toDomain(userJpaEntity1)).thenReturn(new User(1L, "Carlos", "carlos@example.com"));
        when(userMapper.toDomain(userJpaEntity2)).thenReturn(new User(2L, "Jane Doe", "jane.doe@example.com"));

        List<User> allUsers = jpaUserRepository.findAll();

        assertThat(allUsers, hasSize(2));
        assertThat(allUsers, contains(
                hasProperty("id", is(1L)),
                hasProperty("id", is(2L))
        ));
    }
}
