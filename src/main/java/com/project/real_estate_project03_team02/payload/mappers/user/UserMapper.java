package com.project.real_estate_project03_team02.payload.mappers.user;

import com.project.real_estate_project03_team02.entity.concretes.user.User;
import com.project.real_estate_project03_team02.payload.request.user.UserRequest;
import com.project.real_estate_project03_team02.payload.request.user.UserRequestForManager;
import com.project.real_estate_project03_team02.payload.response.user.UserResponse;
import org.springframework.stereotype.Component;


/**
 * This class provides mapping functionalities for converting between User entities, User request payloads,
 * and User response payloads.
 */
@Component
public class UserMapper {

	/**
	 * Maps a UserRequest object to a User entity.
	 *
	 * @param userRequest The UserRequest object containing user information.
	 * @return A User entity mapped from the provided UserRequest.
	 */
	public User mapUserRequestToUser(UserRequest userRequest){
		return User.builder()
				.firstName(userRequest.getFirstName())
				.lastName(userRequest.getLastName())
				.email(userRequest.getEmail())
				.phone(userRequest.getPhone())
				.passwordHash(userRequest.getPasswordHash())
				.build();
	}
	public User mapUserRequestForManagerToUser(UserRequestForManager userRequestForManager){
		return User.builder()
				.firstName(userRequestForManager.getFirstName())
				.lastName(userRequestForManager.getLastName())
				.email(userRequestForManager.getEmail())
				.phone(userRequestForManager.getPhone())
				.build();
	}

	/**
	 * Maps a User entity to a UserResponse object.
	 *
	 * @param user The User entity to be mapped.
	 * @return A UserResponse object mapped from the provided User entity.
	 */
	public UserResponse mapUserToUserResponse(User user){
		return UserResponse.builder()
				.userId(user.getId())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.phone(user.getPhone())
				.email(user.getEmail())
				.build();
	}
}

