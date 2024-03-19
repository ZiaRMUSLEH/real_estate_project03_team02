package com.project.real_estate_project03_team02.payload.request.user;

import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import javax.validation.constraints.Size;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestForManager {

    @NotNull(message= ErrorMessages.ENTER_FIRST_NAME)
    @Size(min=2,message = ErrorMessages.MIN_LENGTH_FIRST_NAME)
    @Size(max = 30,message = ErrorMessages.MAX_LENGTH_FIRST_NAME)
    private String firstName;
    @NotNull(message= ErrorMessages.ENTER_LAST_NAME)
    @Size(min=2,message = ErrorMessages.MIN_LENGTH_LAST_NAME)
    @Size(max = 30,message = ErrorMessages.MAX_LENGTH_LAST_NAME)
    private String lastName;
    @NotNull(message= ErrorMessages.EMAIL_NOT_EMPTY)
    @Size(min=10,message = ErrorMessages.MIN_LENGTH_EMAIL)
    @Size(max = 80,message = ErrorMessages.MAX_LENGTH_EMAIL)
    @Email(message = ErrorMessages.INVALID_EMAIL_FORMAT)
    private String email;
    @NotNull(message= ErrorMessages.ENTER_PHONE)
    private String phone;

}
