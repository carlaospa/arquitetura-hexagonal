package com.carlaospa.arquiteturahexagonal.domain.port.in;

import com.carlaospa.arquiteturahexagonal.domain.model.User;

import java.util.List;

public interface UserServicePort {
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(Long userId);
    User getUserById(Long userId);

    List<User> getAllUsers();
}
