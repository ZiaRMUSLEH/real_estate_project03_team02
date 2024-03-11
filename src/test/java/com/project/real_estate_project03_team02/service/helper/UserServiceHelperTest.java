package com.project.real_estate_project03_team02.service.helper;
import com.project.real_estate_project03_team02.entity.concretes.user.User;
import com.project.real_estate_project03_team02.exception.BadRequestException;
import com.project.real_estate_project03_team02.exception.ConflictException;
import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import com.project.real_estate_project03_team02.repository.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;

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
        assertDoesNotThrow(() -> userServiceHelper.checkDuplicate(email));
        verify(userRepository, times(1)).existsByEmail(email);
    }

    @Test
    void checkDuplicateWhenValueIsDuplicateShouldThrowConflictException() {
        when(userRepository.existsByEmail(email)).thenReturn(true);
        ConflictException exception = assertThrows(ConflictException.class, () -> userServiceHelper.checkDuplicate(email));
        assertEquals(String.format(ErrorMessages.ALREADY_REGISTER_MESSAGE_EMAIL, email), exception.getMessage());
        verify(userRepository, times(1)).existsByEmail(email);
    }

    @Test
    void generateResetCodeShouldReturnCodeWithGivenLength() {
        int length = 10;
        String resetCode = userServiceHelper.generateResetCode(length);
        assertNotNull(resetCode);
        assertEquals(length, resetCode.length());
    }
    @Test
    void getUserResetCodeShouldReturnUserForValidResetCode() {
        String resetCode = "validResetCode";
        User expectedUser = new User();
        when(userRepository.findByResetPasswordCode(resetCode)).thenReturn(java.util.Optional.of(expectedUser));
        User resultUser = userServiceHelper.getUserResetCode(resetCode);
        assertNotNull(resultUser);
        assertEquals(expectedUser, resultUser);
    }
    @Test
    void getUserResetCodeShouldReturnNullForInvalidResetCode() {
        String resetCode = "invalidResetCode";
        when(userRepository.findByResetPasswordCode(resetCode)).thenReturn(java.util.Optional.empty());
        User resultUser = userServiceHelper.getUserResetCode(resetCode);
        assertNull(resultUser);
    }
    @Test
    void getUserFromUsernameAttributeShouldThrowBadRequestExceptionForNullUsername() {
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        when(httpServletRequest.getAttribute("username")).thenReturn(null);
        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> userServiceHelper.getUserFromUsernameAttribute(httpServletRequest));
        assertEquals(ErrorMessages.USER_NOT_FOUND, exception.getMessage());
    }

    @Test
    void getUserFromUsernameAttributeShouldThrowBadRequestExceptionForBuiltInUser() {
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        String username = "builtInUser";
        when(httpServletRequest.getAttribute("username")).thenReturn(username);
        User builtInUser = new User();
        builtInUser.setBuiltIn(true);
        when(userRepository.findByEmailEquals(username)).thenReturn(java.util.Optional.of(builtInUser));
        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> userServiceHelper.getUserFromUsernameAttribute(httpServletRequest));
        assertEquals(ErrorMessages.USER_IS_BUILT_IN, exception.getMessage());
    }

    @Test
    void getUserFromUsernameAttributeShouldReturnUserForValidUsername() {
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        String username = "validUser";
        when(httpServletRequest.getAttribute("username")).thenReturn(username);
        User expectedUser = new User();
        when(userRepository.findByEmailEquals(username)).thenReturn(java.util.Optional.of(expectedUser));
        User resultUser = userServiceHelper.getUserFromUsernameAttribute(httpServletRequest);
        assertNotNull(resultUser);
        assertEquals(expectedUser, resultUser);
    }










}
