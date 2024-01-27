package com.project.real_estate_project03_team02.controller.user;

import com.project.real_estate_project03_team02.payload.request.user.LoginRequest;
import com.project.real_estate_project03_team02.payload.request.user.UserRequest;
import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.payload.response.user.LoginResponse;
import com.project.real_estate_project03_team02.payload.response.user.UserResponse;
import com.project.real_estate_project03_team02.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAuthenticateUser() {
        // Mocking the userService.loginUser method
        LoginRequest loginRequest = new LoginRequest("test@example.com", "password");
        LoginResponse loginResponse = new LoginResponse("testToken");
        when(userService.loginUser(any(LoginRequest.class))).thenReturn(
                new ResponseEntity<>(HttpStatus.OK));

        // Call the controller method
        ResponseEntity<LoginResponse> response = userController.authenticateUser(loginRequest);

        // Assert the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testSaveUser() {
        // Mocking the userService.save method
        UserRequest userRequest = new UserRequest("John", "Doe", "123456789012", "john.doe@example.com", "password");
        UserResponse userResponse= new UserResponse(1L,"John", "Doe", "123456789012", "john.doe@example.com");
        when(userService.save(any(UserRequest.class))).thenReturn(
                new ResponseMessage<>(userResponse, "User saved successfully", HttpStatus.CREATED)
        );

        // Call the controller method
        ResponseMessage<UserResponse> response = userController.save(userRequest);

        // Assert the result
        assertEquals(userResponse,response.getObject());
        assertEquals(HttpStatus.CREATED, response.getHttpStatus());
        assertEquals("User saved successfully", response.getMessage());
    }
}
