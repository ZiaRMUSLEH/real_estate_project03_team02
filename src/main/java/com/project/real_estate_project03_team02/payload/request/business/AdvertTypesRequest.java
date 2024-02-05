package com.project.real_estate_project03_team02.payload.request.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdvertTypesRequest {

    @NotNull(message = "Title must not be empty")
    private String title;
}
