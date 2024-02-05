package com.project.real_estate_project03_team02.payload.response.business;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdvertTypesResponse {
    private Long id;

    private String title;
}
