package com.project.real_estate_project03_team02.payload.request;

import com.project.real_estate_project03_team02.utilis.Messages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotNull(message = Messages.EMAIL_NOT_EMPTY)
    private String email;
    @NotNull(message = Messages.PASS_NOT_EMPTY)
    private String password;
}
