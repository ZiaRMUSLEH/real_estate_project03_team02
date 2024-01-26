package com.project.real_estate_project03_team02.service.user;


import com.project.real_estate_project03_team02.entity.concretes.user.Role;
import com.project.real_estate_project03_team02.entity.concretes.user.User;
import com.project.real_estate_project03_team02.entity.enums.RoleType;
import com.project.real_estate_project03_team02.exception.ResourceNotFoundException;
import com.project.real_estate_project03_team02.payload.mappers.user.UserMapper;
import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import com.project.real_estate_project03_team02.payload.messages.SuccessMessages;
import com.project.real_estate_project03_team02.payload.request.user.LoginRequest;
import com.project.real_estate_project03_team02.payload.request.user.UserRequest;
import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.payload.response.user.AuthResponse;
import com.project.real_estate_project03_team02.payload.response.user.LoginResponse;
import com.project.real_estate_project03_team02.payload.response.user.UserResponse;
import com.project.real_estate_project03_team02.repository.user.UserRepository;
import com.project.real_estate_project03_team02.security.jwt.JwtUtils;
import com.project.real_estate_project03_team02.service.helper.CheckDuplicateHelper;
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
	private final CheckDuplicateHelper checkDuplicateHelper;
	private final UserRoleService userRoleService;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	public final JwtUtils jwtUtils;
	public final AuthenticationManager authenticationManager;


	public ResponseMessage<UserResponse> save(UserRequest userRequest) {
		checkDuplicateHelper.checkDuplicate(userRequest.getEmail());
		User user =userMapper.mapUserRequestToUser(userRequest);
		user.setPasswordHash(passwordEncoder.encode(userRequest.getPasswordHash()));
		Set<Role> userRoles = Set.of(userRoleService.getUserRole(RoleType.CUSTOMER));
		user.setUserRoles(userRoles);
		user.setCreateAt(LocalDateTime.now());
		User savedUser=userRepository.save(user);
		return ResponseMessage.<UserResponse>builder()
				.message(SuccessMessages.USER_SAVED)
				.httpStatus(HttpStatus.CREATED)
				.object(userMapper.mapUserToUserResponse(savedUser))
				.build();
	}


	public ResponseMessage<LoginResponse> loginUser(LoginRequest loginRequest) {
		String email = loginRequest.getEmail();
		String password = loginRequest.getPassword();
		Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token =jwtUtils.generateJwtToken(authentication);
		return ResponseMessage.<LoginResponse>builder()
				.message(SuccessMessages.USER_LOGIN)
				.httpStatus(HttpStatus.OK)
				.object(new LoginResponse(token))
				.build();


	}


    public User findById(Long id) {
		return userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_USER_MESSAGE,id)));
    }
}