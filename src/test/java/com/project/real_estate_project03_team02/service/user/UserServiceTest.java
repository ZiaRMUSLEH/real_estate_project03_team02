package com.project.real_estate_project03_team02.service.user;

import com.project.real_estate_project03_team02.entity.concretes.user.Role;
import com.project.real_estate_project03_team02.entity.concretes.user.User;
import com.project.real_estate_project03_team02.entity.enums.RoleType;
import com.project.real_estate_project03_team02.exception.ResourceNotFoundException;
import com.project.real_estate_project03_team02.payload.mappers.user.UserMapper;
import com.project.real_estate_project03_team02.payload.messages.SuccessMessages;
import com.project.real_estate_project03_team02.payload.request.user.LoginRequest;
import com.project.real_estate_project03_team02.payload.request.user.UserRequest;
import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.payload.response.user.LoginResponse;
import com.project.real_estate_project03_team02.payload.response.user.UserResponse;
import com.project.real_estate_project03_team02.repository.user.UserRepository;
import com.project.real_estate_project03_team02.security.jwt.JwtUtils;
import com.project.real_estate_project03_team02.service.helper.UserServiceHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;


import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserServiceHelper userServiceHelper;

    @Mock
    private UserRoleService userRoleService;
    @Mock
    private EmailService emailService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUser() {
        Role role = new Role(1L,RoleType.CUSTOMER,null);
        Set<Role> userRoles = Set.of(role);
        UserRequest userRequest = new UserRequest("John", "Doe", "123456789012", "john.doe@example.com", "password");
        UserResponse userResponse =new UserResponse(1L,"John", "Doe", "123456789012", "john.doe@example.com",userRoles);

        User user = new User(1L,"John","Doe","john.doe@example.com","123456789012","encodedPassword",null, false, LocalDateTime.now(), null, userRoles);
        doNothing().when(userServiceHelper).checkDuplicate(user.getEmail());
        when(userMapper.mapUserRequestToUser(userRequest)).thenReturn(user);
        when(passwordEncoder.encode(userRequest.getPasswordHash())).thenReturn("encodedPassword");
        when(userRoleService.getUserRole(RoleType.CUSTOMER)).thenReturn(role);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.mapUserToUserResponse(user)).thenReturn(userResponse);

        ResponseMessage<UserResponse> response = userService.save(userRequest);

        assertEquals(HttpStatus.CREATED, response.getHttpStatus());
        assertEquals(SuccessMessages.USER_SAVED, response.getMessage());
        assertEquals(userResponse,response.getObject());
    }

    @Test
    void testLoginUser() {
        LoginRequest loginRequest = new LoginRequest("test@example.com", "password");
       LoginResponse loginResponse=new LoginResponse("testToken");
        Authentication authentication =mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(jwtUtils.generateJwtToken(authentication)).thenReturn(loginResponse.getToken());

        ResponseEntity<LoginResponse> response = userService.loginUser(loginRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(loginResponse, response.getBody());
    }

    @Test
    void testFindByIdWhenUserExists() {
        Long userId = 1L;
        User user = new User(userId, "John", "Doe", "john.doe@example.com", "123456789012", "encodedPassword", null, false, LocalDateTime.now(), null, Set.of());

        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));

        User result = userService.findById(userId);

        assertNotNull(result);
        assertEquals(userId, result.getId());
    }

    @Test
    void testFindByIdWhenUserDoesNotExist() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(java.util.Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.findById(userId));
    }


}
