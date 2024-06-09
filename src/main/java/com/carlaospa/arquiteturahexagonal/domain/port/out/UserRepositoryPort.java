package com.carlaospa.arquiteturahexagonal.domain.port.out;

import com.carlaospa.arquiteturahexagonal.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {
    User save(User user);
    void deleteById(Long userId);
    Optional<User> findById(Long userId);

    List<User> findAll();
}
