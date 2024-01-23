package com.project.real_estate_project03_team02.payload.request;


import com.project.real_estate_project03_team02.utilis.Messages;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {

    @NotNull(message= Messages.ENTER_FIRST_NAME)
    @Size(min=2,message = Messages.MIN_LENGTH_FIRST_NAME)
    @Size(max = 30,message = Messages.MAX_LENGTH_FIRST_NAME)
    private String firstName;
    @NotNull(message= Messages.ENTER_LAST_NAME)
    @Size(min=2,message = Messages.MIN_LENGTH_LAST_NAME)
    @Size(max = 30,message = Messages.MAX_LENGTH_LAST_NAME)
    private String lastName;
    @NotNull(message= Messages.EMAIL_NOT_EMPTY)
    @Size(min=10,message = Messages.MIN_LENGTH_EMAIL)
    @Size(max = 80,message = Messages.MAX_LENGTH_EMAIL)
    @Pattern(regexp = Messages.EMAIL_REGEX, message = Messages.INVALID_EMAIL_FORMAT)
    private String email;
    @NotNull(message= Messages.ENTER_PHONE)
    private String phone;
    @NotNull(message= Messages.PASS_NOT_EMPTY)
    @Size(min=8,message = Messages.MIN_LENGTH_PASS)
    @Size(max=60,message = Messages.MAX_LENGTH_PASS)
    @Pattern(regexp = Messages.PASS_REGEX, message =Messages.INVALID_PASS_FORMAT )
    private String passwordHash;

}
