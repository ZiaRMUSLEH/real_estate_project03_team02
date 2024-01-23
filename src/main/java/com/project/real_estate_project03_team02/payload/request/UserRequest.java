package com.project.real_estate_project03_team02.payload.request;


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

    @NotNull(message= "please enter your first name")
    @Size(min=2,message = "Your first name should be at least 2 characters")
    @Size(max = 30,message = "Your first name should be at most 30 characters")
    private String firstName;
    @NotNull(message= "please enter your last name")
    @Size(min=2,message = "Your last name should be at least 2 characters")
    @Size(max = 30,message = "Your last name should be at most 30 characters")
    private String lastName;
    @NotNull(message= "please enter your email")
    @Size(min=10,message = "Your email should be at least 10 characters")
    @Size(max = 80,message = "Your email should be at most 80 characters")
    @Pattern(regexp = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", message = "Invalid email format")
    private String email;
    @NotNull(message= "please enter your phone number")
    private String phone;
    @NotNull(message= "please enter your password")
    @Size(min=8,message = "Your password should be at least 8 characters")
    @Size(max=60,message = "Your password should be max 60 characters")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Your password must consist of the characters .")
    private String passwordHash;

}
