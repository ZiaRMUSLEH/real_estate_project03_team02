package com.project.real_estate_project03_team02.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotNull(message = "Username must not be empty")
    private String email;
    @NotNull(message = "Password must not be empty")
    private String password;
}
