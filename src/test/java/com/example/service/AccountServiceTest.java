
package com.example.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.repo.accountRepo;

class AccountServiceTest {

    @InjectMocks
    private accountService accountService;

    @Mock
    private accountRepo accountRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testLogin_ValidCredentials_ReturnsTrue() {
        // Arrange
        String username = "devika";
        String password = "devika";

        // Act
        boolean result = accountService.login(username, password);

        // Assert
        assertTrue(result);
    }

    @Test
    void testLogin_InvalidCredentials_ReturnsFalse() {
        // Arrange
        String username = "invalidUser";
        String password = "invalidPassword";

        // Act
        boolean result = accountService.login(username, password);

        // Assert
        assertFalse(result);
    }

    @Test
    void testDelete_ValidUsername_DeletesAccount() {
        // Arrange
        String username = "devika";

        // Act
        accountService.delete(username);

        // Assert
        verify(accountRepo, times(1)).deleteById(username);
    }
}
