package com.project.real_estate_project03_team02.service.business;

import com.project.real_estate_project03_team02.entity.concretes.business.CategoryPropertyKey;
import com.project.real_estate_project03_team02.entity.concretes.business.CategoryPropertyValue;
import com.project.real_estate_project03_team02.entity.concretes.business.City;
import com.project.real_estate_project03_team02.exception.ResourceNotFoundException;
import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import com.project.real_estate_project03_team02.payload.request.business.AdvertRequest;
import com.project.real_estate_project03_team02.repository.business.CategoryPropertyValueRepository;
import com.project.real_estate_project03_team02.repository.business.CityRepository;
import com.project.real_estate_project03_team02.service.helper.CategoryServiceHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CategoryPropertyValueService {

    private final CategoryPropertyValueRepository categoryPropertyValueRepository;

    private final CategoryServiceHelper categoryServiceHelper;

    public CategoryPropertyValue findByCategoryPropertyKey(CategoryPropertyKey categoryPropertyKey){
        return categoryPropertyValueRepository.findByCategoryPropertyKey(categoryPropertyKey);
    }


    public void saveCategoryPropertyValue(AdvertRequest advertRequest){
        advertRequest.getProperties().forEach(property -> {
            property.forEach((keyId, value) -> {

                CategoryPropertyKey categoryPropertyKey = categoryServiceHelper.findCategoryPropertyKeyById(keyId);
                CategoryPropertyValue categoryPropertyValue = findByCategoryPropertyKey(categoryPropertyKey);
                categoryPropertyValue.setValue(value);
                save(categoryPropertyValue);
            });
        });
    }

    public void save(CategoryPropertyValue categoryPropertyValue) {
        categoryPropertyValueRepository.save(categoryPropertyValue);
    }



}
