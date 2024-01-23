package com.project.real_estate_project03_team02.payload.mappers.user;

import com.project.real_estate_project03_team02.entity.concretes.user.User;
import com.project.real_estate_project03_team02.payload.request.user.UserRequest;
import com.project.real_estate_project03_team02.payload.response.user.UserResponse;
import org.springframework.stereotype.Component;

/**
 * mapper for Admin
 * @author yaprak
 * @version 0.0.1
 */
@Component
public class UserMapper {


	public User mapUserRequestToUser(UserRequest userRequest){
		return User.builder()
				.firstName(userRequest.getFirstName())
				.lastName(userRequest.getLastName())
				.email(userRequest.getEmail())
				.phone(userRequest.getPhone())
				.passwordHash(userRequest.getPassword())
				.build();
	}

	public UserResponse mapAdminToAdminResponse(User user){
		return UserResponse.builder()
				.userId(user.getId())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.phone(user.getPhone())
				.email(user.getEmail())
				.build();
	}


}
