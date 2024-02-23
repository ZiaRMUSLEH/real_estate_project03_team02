package com.project.real_estate_project03_team02.payload.response.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactMessageResponse {


    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String message;
}
