package com.carlaospa.arquiteturahexagonal.infrastructure.adapter.out.persistence;

import com.carlaospa.arquiteturahexagonal.domain.model.User;
import com.carlaospa.arquiteturahexagonal.domain.port.out.UserRepositoryPort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JpaUserRepository implements UserRepositoryPort {

    private final SpringDataJpaUserRepository springDataJpaUserRepository;
    private final UserMapper userMapper;

    public JpaUserRepository(SpringDataJpaUserRepository springDataJpaUserRepository, UserMapper userMapper) {
        this.springDataJpaUserRepository = springDataJpaUserRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User save(User user) {
        UserJpaEntity userJpaEntity = userMapper.toJpaEntity(user);
        userJpaEntity = springDataJpaUserRepository.save(userJpaEntity);
        return userMapper.toDomain(userJpaEntity);
    }

    @Override
    public void deleteById(Long userId) {
        springDataJpaUserRepository.deleteById(userId);
    }

    @Override
    public Optional<User> findById(Long userId) {
        Optional<UserJpaEntity> userJpaEntity = springDataJpaUserRepository.findById(userId);
        return userJpaEntity.map(userMapper::toDomain);
    }

    @Override
    public List<User> findAll() {
        List<UserJpaEntity> userJpaEntities = springDataJpaUserRepository.findAll();
        return userJpaEntities.stream()
                .map(userMapper::toDomain)
                .collect(Collectors.toList());
    }
}
