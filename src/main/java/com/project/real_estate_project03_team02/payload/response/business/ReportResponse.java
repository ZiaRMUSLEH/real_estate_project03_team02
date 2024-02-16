package com.project.real_estate_project03_team02.payload.response.business;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.real_estate_project03_team02.entity.concretes.business.Category;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReportResponse {
//    categories: 1231,
//    brands: 234,
//    products: 12,
//    offers: 1324,
//    customers: 124,
    private Long id;

   // private Category
}
