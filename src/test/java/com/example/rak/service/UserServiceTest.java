package com.example.rak.service;

import com.example.rak.modal.Users;
import com.example.rak.repository.UsersRepository;
import com.example.rak.services.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UsersRepository userRepository;

    @InjectMocks
    private UsersService userService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private Users user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new Users();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john@example.com");
        user.setPassword(passwordEncoder.encode("password123"));
    }

    @Test
    void testCreateUser() {
        when(userRepository.save(any(Users.class))).thenReturn(user);
        Users createdUser = userService.createUser(user);
        assertNotNull(createdUser);
        assertEquals(user.getId(), createdUser.getId());
    }

    @Test
    void testGetUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Optional<Users> foundUser = userService.getUserById(1L);
        assertTrue(foundUser.isPresent());
        assertEquals(user.getId(), foundUser.get().getId());
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userRepository).deleteById(1L);
        userService.deleteUserById(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void testChangePassword_Success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Optional<Users> isChanged = userService.updateUserPassword(1L, "newPassword123");
        assertTrue(isChanged.isPresent());
    }

    @Test
    void testChangePassword_Failure() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        assertThrows(IllegalArgumentException.class, () -> {
            userService.updateUserPassword(1L,  "newPassword123");
        });
    }
}
