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
public class AdvertTypeRequest {

    @Size( max = 30)
    @NotNull(message = "Title must not be empty")
    private String title;
}
