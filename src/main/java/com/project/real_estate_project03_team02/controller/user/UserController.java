package com.project.real_estate_project03_team02.controller.user;

import com.project.real_estate_project03_team02.payload.request.user.LoginRequest;
import com.project.real_estate_project03_team02.payload.request.user.ResetPasswordRequest;
import com.project.real_estate_project03_team02.payload.request.user.UserForgotPasswordRequest;
import com.project.real_estate_project03_team02.payload.request.user.UserRequest;
import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.payload.response.user.LoginResponse;
import com.project.real_estate_project03_team02.payload.response.user.UserResponse;
import com.project.real_estate_project03_team02.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticateUser(@RequestBody @Valid LoginRequest loginRequest){
        return userService.loginUser(loginRequest);
    }



    @PostMapping("/register")
    public ResponseMessage<UserResponse> save(@RequestBody @Valid UserRequest userRequest){
        return userService.save(userRequest);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Void> forgotPassword(@RequestBody @Valid UserForgotPasswordRequest forgotPasswordRequest) {
        userService.forgotPassword(forgotPasswordRequest.getEmail());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ResponseMessage<String>> resetPassword(@RequestBody @Valid ResetPasswordRequest resetPasswordRequest) {
        ResponseMessage<String> responseMessage = userService.resetPassword(resetPasswordRequest);
        return ResponseEntity.status(responseMessage.getHttpStatus()).body(new ResponseMessage<>(null,responseMessage.getMessage(),null));
    }

    @GetMapping("/auth")
    public ResponseMessage<UserResponse> getUserInfo(HttpServletRequest httpServletRequest) {
        return userService.getUserInfo(httpServletRequest);
    }



}

