package com.carlaospa.arquiteturahexagonal.application.service;

import com.carlaospa.arquiteturahexagonal.domain.model.User;
import com.carlaospa.arquiteturahexagonal.domain.port.in.UserServicePort;
import com.carlaospa.arquiteturahexagonal.domain.port.out.UserRepositoryPort;

import java.util.List;

public class UserService implements UserServicePort {

    private final UserRepositoryPort userRepositoryPort;

    public UserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User createUser(User user) {
        return userRepositoryPort.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepositoryPort.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
       userRepositoryPort.deleteById(userId);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepositoryPort.findById(userId).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepositoryPort.findAll();
    }
}
