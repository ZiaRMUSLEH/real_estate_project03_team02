package com.project.real_estate_project03_team02.payload.mappers;


import com.project.real_estate_project03_team02.entity.concretes.User;
import com.project.real_estate_project03_team02.payload.request.UserRequest;
import com.project.real_estate_project03_team02.payload.response.UserResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserMapper {



    public UserResponse mapUserToUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .createAt(user.getCreateAt())
                .build();
    }




    public User mapUserRequestToUser(UserRequest userRequest){
        return User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .phone(userRequest.getPhone())
                .passwordHash(userRequest.getPasswordHash())
                .build();
    }






}
