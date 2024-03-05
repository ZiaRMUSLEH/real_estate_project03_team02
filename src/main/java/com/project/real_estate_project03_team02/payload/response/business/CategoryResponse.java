package com.project.real_estate_project03_team02.payload.response.business;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {
    private Long id;
    private String title;
    private String icon;
    private int seq;
    private String slug;
    private boolean isActive;
}
