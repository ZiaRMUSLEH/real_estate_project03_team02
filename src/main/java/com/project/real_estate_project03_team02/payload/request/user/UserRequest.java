package com.project.real_estate_project03_team02.payload.request.user;


import lombok.*;
import javax.validation.constraints.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {


    @NotNull(message = "Please enter your first name")
    @Size(min = 2, max = 30,message = "Your name should be at least 2 chars")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Your first name must consist of the characters .")
    private String firstName;

    @NotNull(message = "Please enter your last name")
    @Size(min = 2, max = 30,message = "Your surname should be at least 2 chars")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Your last name must consist of the characters .")
    private String lastName;


    @NotNull(message = "Please enter your phone number")
    @Size(min = 12, max = 12,message = "Your phone number should be 12 characters long")
    @Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$",
            message = "Please enter valid phone number")
    private String phone;


    @NotNull(message = "Please enter your email")
    @Email(message = "Please enter valid email")
    @Size(min=10, max=80 , message = "Your email should be between 10 and 80 chars")
    private String email;


    @NotNull(message = "Please enter your password")
    @Size(min = 8, max = 60,message = "Your password should be at least 8 chars or maximum 60 characters")
    //@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\\\d)(?=.*[-+_!@#$%^&*., ?]).+$")
    //@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$")
    private String password;




}
