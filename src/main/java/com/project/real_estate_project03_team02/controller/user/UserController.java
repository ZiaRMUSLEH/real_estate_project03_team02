package com.project.real_estate_project03_team02.controller.user;

import com.project.real_estate_project03_team02.payload.request.user.*;
import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.payload.response.user.LoginResponse;
import com.project.real_estate_project03_team02.payload.response.user.UserResponse;
import com.project.real_estate_project03_team02.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAnyAuthority('CUSTOMER','ADMIN','MANAGER')")
    @GetMapping("/auth")
    public ResponseMessage<UserResponse> getAuthUserInfo(HttpServletRequest httpServletRequest) {
        return userService.getUserInfo(httpServletRequest);
    }
    @PreAuthorize("hasAnyAuthority('CUSTOMER','ADMIN','MANAGER')")
    @PutMapping("/auth")
    public ResponseMessage<UserResponse> updateAuthUserInfo(HttpServletRequest httpServletRequest,@RequestBody @Valid UserRequest userRequest) {
        return userService.updateUserInfo(httpServletRequest,userRequest);
    }
    @PreAuthorize("hasAnyAuthority('CUSTOMER','ADMIN','MANAGER')")
    @PatchMapping("/auth")
    public  ResponseEntity<Void> updateAuthUserPassword(HttpServletRequest httpServletRequest,@RequestBody @Valid ChangePasswordRequest changePasswordRequest) {
        return userService.updateUserPassword(httpServletRequest,changePasswordRequest);
    }

    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    @DeleteMapping("/auth")
    public  ResponseEntity<Void> deleteAuthUser(HttpServletRequest httpServletRequest,@RequestBody @Valid DeleteUserRequest deleteUserRequest) {
        return userService.deleteAuthUser(httpServletRequest,deleteUserRequest);
    }
    @GetMapping("/admin")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public Page<UserResponse> getAllUsersForManagers(@RequestParam(required = false) String q,
                                                              @RequestParam(value = "page", defaultValue = "0") int page,
                                                              @RequestParam(value = "size", defaultValue = "20") int size,
                                                              @RequestParam(value = "sort", defaultValue = "createAt") String sort,
                                                              @RequestParam(value = "type", defaultValue = "desc") String type) {
        return userService.getAllUsersForManagers( q,page, size, sort, type);
    }
    @GetMapping("/{id}/admin")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public UserResponse getUserForManagersById(@PathVariable Long id) {
        return userService.getUserForManagersById(id);
    }
    @PutMapping("/{id}/admin")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseMessage<UserResponse> updateUserForManagersById(HttpServletRequest httpServletRequest,@PathVariable Long id,@RequestBody @Valid UserRequestForManager userRequestForManager) {
        return userService.updateUserForManagersById(id,userRequestForManager,httpServletRequest);
    }
    @DeleteMapping("/{id}/admin")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseMessage<UserResponse> deleteUserForManagersById(HttpServletRequest httpServletRequest,@PathVariable Long id) {
        return userService.deleteUserForManagersById(id,httpServletRequest);
    }


}

