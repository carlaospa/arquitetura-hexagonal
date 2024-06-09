package com.carlaospa.arquiteturahexagonal.application.service;

import com.carlaospa.arquiteturahexagonal.domain.model.User;
import com.carlaospa.arquiteturahexagonal.domain.port.out.UserRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    private UserRepositoryPort userRepositoryPort;

    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepositoryPort);
    }

    @Test
    void createUser() {
        User user = new User(null, "Carlos", "carlos@example.com");
        when(userRepositoryPort.save(user)).thenReturn(new User(1L, "Carlos", "carlos@example.com"));

        User createdUser = userService.createUser(user);

        assertThat(createdUser.getId(), is(1L));
        assertThat(createdUser.getName(), is("Carlos"));
        assertThat(createdUser.getEmail(), is("carlos@example.com"));
    }

    @Test
    void updateUser() {
        User user = new User(1L, "Carlos", "carlos@example.com");
        when(userRepositoryPort.save(user)).thenReturn(user);

        User updatedUser = userService.updateUser(user);

        assertThat(updatedUser.getId(), is(1L));
        assertThat(updatedUser.getName(), is("Carlos"));
        assertThat(updatedUser.getEmail(), is("carlos@example.com"));
    }

    @Test
    void deleteUser() {
        doNothing().when(userRepositoryPort).deleteById(1L);

        userService.deleteUser(1L);

        verify(userRepositoryPort, times(1)).deleteById(1L);
    }

    @Test
    void getUserById() {
        User user = new User(1L, "Carlos", "carlos@example.com");
        when(userRepositoryPort.findById(1L)).thenReturn(Optional.of(user));

        User foundUser = userService.getUserById(1L);

        assertThat(foundUser.getId(), is(1L));
        assertThat(foundUser.getName(), is("Carlos"));
        assertThat(foundUser.getEmail(), is("carlos@example.com"));
    }

    @Test
    void getAllUsers() {
        User user1 = new User(1L, "Carlos", "carlos@example.com");
        User user2 = new User(2L, "Jane", "jane@example.com");
        List<User> users = Arrays.asList(user1, user2);
        when(userRepositoryPort.findAll()).thenReturn(users);

        List<User> allUsers = userService.getAllUsers();

        assertThat(allUsers, hasSize(2));
        assertThat(allUsers, contains(user1, user2));
    }
}