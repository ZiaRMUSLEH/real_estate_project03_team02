package com.project.real_estate_project03_team02.controller.user;

import com.project.real_estate_project03_team02.payload.request.user.LoginRequest;
import com.project.real_estate_project03_team02.payload.request.user.UserRequest;
import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.payload.response.user.AuthResponse;
import com.project.real_estate_project03_team02.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;


    @PostMapping("/login")
    public ResponseMessage<AuthResponse> authenticateUser(@RequestBody @Valid LoginRequest loginRequest){
        return userService.loginUser(loginRequest);
    }


    @PostMapping("/register")//http://localhost:8080/user/register
    public ResponseMessage<?> save(@RequestBody @Valid UserRequest userRequest){
        return userService.save(userRequest);
    }


}

