package com.project.real_estate_project03_team02.payload.response.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactResponse {


    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String message;
}
