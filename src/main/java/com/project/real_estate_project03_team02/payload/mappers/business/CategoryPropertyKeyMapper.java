package com.project.real_estate_project03_team02.payload.mappers.business;

import com.project.real_estate_project03_team02.entity.concretes.business.CategoryPropertyKey;
import com.project.real_estate_project03_team02.payload.request.business.CategoryPropertyKeyRequest;
import com.project.real_estate_project03_team02.payload.response.business.CategoryPropertyKeyResponse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
public class CategoryPropertyKeyMapper {

    @Autowired
    private final CategoryMapper categoryMapper;
    public CategoryPropertyKey mapCategoryPropertyKeyRequestToCategoryPropertyKey(CategoryPropertyKeyRequest categoryPropertyKeyRequest){
        return CategoryPropertyKey.builder()
                .name(categoryPropertyKeyRequest.getName())
                .build();
    }
    public CategoryPropertyKeyResponse mapCategoryPropertyKeyToCategoryPropertyKeyResponse(CategoryPropertyKey categoryPropertyKey){
        return CategoryPropertyKeyResponse.builder()
                .id(categoryPropertyKey.getId())
                .name(categoryPropertyKey.getName())
                .categoryId(categoryMapper.mapCategoryToCategoryResponse(categoryPropertyKey.getCategoryId()))
                .build();
    }
    public List<CategoryPropertyKeyResponse> mapCategoryPropertyKeyListToResponseList(List<CategoryPropertyKey> categoryPropertyKeyList) {
        return categoryPropertyKeyList.stream()
                .map(this::mapCategoryPropertyKeyToCategoryPropertyKeyResponse)
                .collect(Collectors.toList());
    }
}
