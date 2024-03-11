package com.project.real_estate_project03_team02.payload.response.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Category;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryPropertyKeyResponse {


    private Long id;
    private String name;
    private CategoryResponse categoryId;
}
