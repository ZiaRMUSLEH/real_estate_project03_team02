package com.project.real_estate_project03_team02.service;


import com.project.real_estate_project03_team02.entity.concretes.Role;
import com.project.real_estate_project03_team02.entity.concretes.User;
import com.project.real_estate_project03_team02.entity.enums.RoleType;
import com.project.real_estate_project03_team02.payload.mappers.UserMapper;
import com.project.real_estate_project03_team02.payload.request.LoginRequest;
import com.project.real_estate_project03_team02.payload.request.UserRequest;
import com.project.real_estate_project03_team02.payload.response.AuthResponse;
import com.project.real_estate_project03_team02.payload.response.ResponseMessage;
import com.project.real_estate_project03_team02.payload.response.UserResponse;
import com.project.real_estate_project03_team02.repository.UserRepository;
import com.project.real_estate_project03_team02.security.jwt.JwtUtils;
import com.project.real_estate_project03_team02.utilis.Messages;
import com.project.real_estate_project03_team02.utilis.ServiceHelpers;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ServiceHelpers serviceHelpers;
    private final RoleService roleService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    public final JwtUtils jwtUtils;
    public final AuthenticationManager authenticationManager;


    public ResponseMessage<UserResponse> save(UserRequest userRequest) {
        serviceHelpers.checkDuplicate(userRequest.getEmail());
        User user =userMapper.mapUserRequestToUser(userRequest);
        user.setPasswordHash(passwordEncoder.encode(userRequest.getPasswordHash()));
        Set<Role> userRoles = Set.of(roleService.getUserRole(RoleType.CUSTOMER));
        user.setRoles(userRoles);
        user.setCreateAt(LocalDateTime.now());
        User savedUser=userRepository.save(user);
        return ResponseMessage.<UserResponse>builder()
                .message(Messages.USERSAVED)
                .httpStatus(HttpStatus.CREATED)
                .object(userMapper.mapUserToUserResponse(savedUser))
                .build();
    }


    public ResponseMessage<AuthResponse> loginUser(LoginRequest loginRequest) {
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token =jwtUtils.generateJwtToken(authentication);
        return ResponseMessage.<AuthResponse>builder()
                .message(Messages.USERLOGIN)
                .httpStatus(HttpStatus.OK)
                .object(new AuthResponse(token))
                .build();


    }
}
