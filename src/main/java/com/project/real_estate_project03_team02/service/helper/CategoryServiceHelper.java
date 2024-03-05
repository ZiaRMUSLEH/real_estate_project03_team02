package com.project.real_estate_project03_team02.service.helper;

import com.project.real_estate_project03_team02.entity.concretes.business.Category;
import com.project.real_estate_project03_team02.entity.concretes.business.CategoryPropertyKey;
import com.project.real_estate_project03_team02.exception.BadRequestException;
import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import com.project.real_estate_project03_team02.repository.business.CategoryPropertyKeyRepository;
import com.project.real_estate_project03_team02.repository.business.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class CategoryServiceHelper {

    private final CategoryRepository categoryRepository;
    private final CategoryPropertyKeyRepository categoryPropertyKeyRepository;

    public Category findCategoryById(Long id){
        return categoryRepository.findById(id).orElseThrow(()->new BadRequestException(String.format(ErrorMessages.NO_CATEGORY_WITH_ID,id)));

    }public CategoryPropertyKey findCategoryPropertyKeyById(Long id){
        return categoryPropertyKeyRepository.findById(id).orElseThrow(()->new BadRequestException(String.format(ErrorMessages.NO_CATEGORY_PROPERTY_KEY_WITH_ID,id)));

    }

    public ArrayList<CategoryPropertyKey> findCategoryPropertyKeyByCategoryId(Category category){
        return categoryPropertyKeyRepository.findAllByCategoryId(category);

    }
}
