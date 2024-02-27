package com.project.real_estate_project03_team02.payload.request.business;

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
public class ContactMessageRequest {


    private Long id;

    @NotNull
    @Size(max = 30)
    private String firstName;

    //public String firstName;


    @NotNull
    @Size(max = 30)
    private String lastName;

    //public String lastName;


    @NotNull
    @Size(max = 60)
    private String email;

    @NotNull
    @Size(max = 300)
    private String message;
}
