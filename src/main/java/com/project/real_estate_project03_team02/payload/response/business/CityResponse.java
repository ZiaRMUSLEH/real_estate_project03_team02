package com.project.real_estate_project03_team02.payload.response.business;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CityResponse {

    private String city;
    private int amount;
}
