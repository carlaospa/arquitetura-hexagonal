package com.carlaospa.arquiteturahexagonal.config;

import com.carlaospa.arquiteturahexagonal.application.service.UserService;
import com.carlaospa.arquiteturahexagonal.domain.port.in.UserServicePort;
import com.carlaospa.arquiteturahexagonal.domain.port.out.UserRepositoryPort;
import com.carlaospa.arquiteturahexagonal.infrastructure.adapter.out.persistence.JpaUserRepository;
import com.carlaospa.arquiteturahexagonal.infrastructure.adapter.out.persistence.SpringDataJpaUserRepository;
import com.carlaospa.arquiteturahexagonal.infrastructure.adapter.out.persistence.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public UserServicePort userServicePort(UserRepositoryPort userRepositoryPort){
        return new UserService(userRepositoryPort);
    }

    @Bean
    public UserRepositoryPort userRepositoryPort(SpringDataJpaUserRepository springDataJpaUserRepository, UserMapper userMapper){
        return new JpaUserRepository(springDataJpaUserRepository, userMapper);
    }
}
