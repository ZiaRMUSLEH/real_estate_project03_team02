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
    String email = "test@example.com";
    @Test
    void checkDuplicateWhenValueIsNotDuplicateShouldNotThrowException() {
        when(userRepository.existsByEmail(email)).thenReturn(false);
        //Assert
        assertDoesNotThrow(() -> userServiceHelper.checkDuplicate(email));
        // Ensure that userRepository.existsByEmail is called once with the provided email
        verify(userRepository, times(1)).existsByEmail(email);
    }

    @Test
    void checkDuplicateWhenValueIsDuplicateShouldThrowConflictException() {
        when(userRepository.existsByEmail(email)).thenReturn(true);
        // Assert
        ConflictException exception = assertThrows(ConflictException.class, () -> userServiceHelper.checkDuplicate(email));
        assertEquals(String.format(ErrorMessages.ALREADY_REGISTER_MESSAGE_EMAIL, email), exception.getMessage());
        // Ensure that userRepository.existsByEmail is called once with the provided email
        verify(userRepository, times(1)).existsByEmail(email);
    }
}
