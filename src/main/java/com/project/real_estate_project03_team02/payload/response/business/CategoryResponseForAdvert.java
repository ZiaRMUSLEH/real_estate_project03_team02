package com.project.real_estate_project03_team02.payload.response.business;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponseForAdvert {

    private String category;
    private int amount;
}
