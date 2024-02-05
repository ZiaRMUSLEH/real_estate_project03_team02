package com.project.real_estate_project03_team02.payload.request.user;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserForgotPasswordRequest {

    @NotBlank
    @Email
    private String email;
}
