package com.example.rak.controller;

import com.example.rak.controler.UsersController;
import com.example.rak.modal.Users;
import com.example.rak.services.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsersControllerTest {

    @Mock
    private UsersService userService;

    @InjectMocks
    private UsersController userController;

    private Users user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new Users();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john@example.com");
        user.setPassword("encodedPassword"); // Assuming this is a mock password
    }

    @Test
    void testCreateUser() {
        when(userService.createUser(any(Users.class))).thenReturn(user);

        ResponseEntity<Users> response = userController.createUsers(user);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(user.getId(), response.getBody().getId());
    }

    @Test
    void testGetUserById() {
        when(userService.getUserById(1L)).thenReturn(Optional.of(user));

        ResponseEntity<Users> response = userController.getUserById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(user.getId(), response.getBody().getId());
    }

    @Test
    void testGetUserById_NotFound() {
        when(userService.getUserById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Users> response = userController.getUserById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userService).deleteUserById(1L);

        ResponseEntity<Void> response = userController.deleteUser(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testChangePassword_Success() {
        when(userService.updateUserPassword(1L, "newPassword123")).thenReturn(Optional.of(user));

        ResponseEntity<Users> response = userController.updateUserPassword(1L, "newPassword123");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testChangePassword_Failure() {
        when(userService.updateUserPassword(1L, "newPassword123")).thenReturn(Optional.empty());

        ResponseEntity<Users> response = userController.updateUserPassword(1L, "newPassword123");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }
}