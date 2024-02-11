package com.project.real_estate_project03_team02.service.user;


import com.project.real_estate_project03_team02.entity.concretes.user.Role;
import com.project.real_estate_project03_team02.entity.concretes.user.User;
import com.project.real_estate_project03_team02.entity.enums.RoleType;
import com.project.real_estate_project03_team02.exception.ResourceNotFoundException;
import com.project.real_estate_project03_team02.payload.mappers.user.UserMapper;
import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import com.project.real_estate_project03_team02.payload.messages.SuccessMessages;
import com.project.real_estate_project03_team02.payload.request.user.LoginRequest;
import com.project.real_estate_project03_team02.payload.request.user.ResetPasswordRequest;
import com.project.real_estate_project03_team02.payload.request.user.UserRequest;
import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.payload.response.user.LoginResponse;
import com.project.real_estate_project03_team02.payload.response.user.UserResponse;
import com.project.real_estate_project03_team02.repository.user.UserRepository;
import com.project.real_estate_project03_team02.security.jwt.JwtUtils;
import com.project.real_estate_project03_team02.service.helper.UserServiceHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final UserServiceHelper userServiceHelper;
	private final UserRoleService userRoleService;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtils jwtUtils;
	private final AuthenticationManager authenticationManager;
	private final EmailService emailService;


	public ResponseMessage<UserResponse> save(UserRequest userRequest) {
		userServiceHelper.checkDuplicate(userRequest.getEmail());
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


	public ResponseEntity<LoginResponse> loginUser(LoginRequest loginRequest) {
		String email = loginRequest.getEmail();
		String password = loginRequest.getPassword();
		Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token =jwtUtils.generateJwtToken(authentication);
		return ResponseEntity.ok(LoginResponse.builder()
				.token(token)
				.build());



	}


	public User findById(Long id) {
		return userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_USER_MESSAGE,id)));
	}

	public void forgotPassword(String email) {
		User user = userRepository.findByEmailEquals(email);

		if (user != null) {
			String resetCode =userServiceHelper.generateResetCode(20);
			user.setResetPasswordCode(resetCode);
			userRepository.save(user);

			emailService.sendPasswordResetEmail(user.getEmail(), resetCode);
		}
	}

	public Boolean existByEmail(String authenticatedUserEmail) {
		return userRepository.existsByEmail(authenticatedUserEmail);
	}

	public User findByEmail(String authenticatedUserEmail) {
		User user = userRepository.findByEmailEquals(authenticatedUserEmail);
		if(user==null){throw new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_USER_MESSAGE_BY_EMAIL,authenticatedUserEmail)); }

		return user;
	}

	//TODO tests..

	public ResponseMessage<String> resetPassword(ResetPasswordRequest resetPasswordRequest) {
		User userWithCodeFromDatabase = userServiceHelper.getUserResetCode(resetPasswordRequest.getCode());
		if (userWithCodeFromDatabase != null) {
				String newPasswordHash = passwordEncoder.encode(resetPasswordRequest.getNewPassword());
			userWithCodeFromDatabase.setPasswordHash(newPasswordHash);
			userWithCodeFromDatabase.setResetPasswordCode(null);
				userRepository.save(userWithCodeFromDatabase);
				return new ResponseMessage<>(null,null, HttpStatus.OK);

		} else {
			return new ResponseMessage<>(null, ErrorMessages.INVALID_RESET_CODE, HttpStatus.BAD_REQUEST);
		}
	}


	public ResponseMessage<UserResponse> getUserInfo(HttpServletRequest httpServletRequest) {
		String userName= (String) httpServletRequest.getAttribute("username");
		User user=userRepository.findByEmailEquals(userName);

		return ResponseMessage.<UserResponse>builder()
				.object(userMapper.mapUserToUserResponse(user))
				.build();


	}
}