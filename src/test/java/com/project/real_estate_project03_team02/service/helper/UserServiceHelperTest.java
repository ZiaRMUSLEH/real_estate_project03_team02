package com.project.real_estate_project03_team02.service.helper;
import com.project.real_estate_project03_team02.exception.ConflictException;
import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import com.project.real_estate_project03_team02.repository.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceHelperTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceHelper userServiceHelper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void checkDuplicateWhenValueIsNotDuplicateShouldNotThrowException() {
        // Arrange
        String email = "test@example.com";
        when(userRepository.existsByEmail(email)).thenReturn(false);

        // Act & Assert
        assertDoesNotThrow(() -> userServiceHelper.checkDuplicate(email));

        // Ensure that userRepository.existsByEmail is called once with the provided email
        verify(userRepository, times(1)).existsByEmail(email);
    }

    @Test
    void checkDuplicateWhenValueIsDuplicateShouldThrowConflictException() {
        // Arrange
        String email = "test@example.com";
        when(userRepository.existsByEmail(email)).thenReturn(true);

        // Act & Assert
        ConflictException exception = assertThrows(ConflictException.class, () -> userServiceHelper.checkDuplicate(email));
        assertEquals(String.format(ErrorMessages.ALREADY_REGISTER_MESSAGE_EMAIL, email), exception.getMessage());

        // Ensure that userRepository.existsByEmail is called once with the provided email
        verify(userRepository, times(1)).existsByEmail(email);
    }

    @Test
    void generateResetCodeShouldReturnCodeWithGivenLength() {
        // Act
        String resetCode = userServiceHelper.generateResetCode(8);

        // Assert
        assertNotNull(resetCode);
        assertEquals(8, resetCode.length());
    }

    @Test
    void generateResetCodeShouldReturnDifferentCodes() {
        // Act
        String resetCode1 = userServiceHelper.generateResetCode(8);
        String resetCode2 = userServiceHelper.generateResetCode(8);

        // Assert
        assertNotEquals(resetCode1, resetCode2);
    }
}