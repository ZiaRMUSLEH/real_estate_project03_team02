package com.project.real_estate_project03_team02.payload.response.user;


import com.project.real_estate_project03_team02.entity.concretes.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {


    private Long userId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private Set<Role> userRole;




}
