package com.project.real_estate_project03_team02.payload.mappers.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Category;
import com.project.real_estate_project03_team02.payload.request.business.CategoryRequest;
import com.project.real_estate_project03_team02.payload.response.business.CategoryResponse;
import com.project.real_estate_project03_team02.payload.response.business.CategoryResponseForAdvert;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CategoryMapper {
    public Category mapCategoryRequestToCategory(CategoryRequest categoryRequest){
        return Category.builder()
                .title(categoryRequest.getTitle())
                .icon(categoryRequest.getIcon())
                .build();
    }
    public CategoryResponse mapCategoryToCategoryResponse(Category category){
        return CategoryResponse.builder()
                .id(category.getId())
                .title(category.getTitle())
                .icon(category.getIcon())
                .seq(category.getSeq())
                .slug(category.getSlug())
                .isActive(category.isActive())
                .build();
    }


    public CategoryResponseForAdvert mapToCategoryResponseForAdvert(Object[] objArray) {
        String category = (String) objArray[0];
        int amount = (int) objArray[1];
        return new CategoryResponseForAdvert(category, amount);
    }

}
