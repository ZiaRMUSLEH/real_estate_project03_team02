package com.project.real_estate_project03_team02.service.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.entity.concretes.business.CategoryPropertyKey;
import com.project.real_estate_project03_team02.entity.concretes.business.CategoryPropertyValue;
import com.project.real_estate_project03_team02.exception.ResourceNotFoundException;
import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import com.project.real_estate_project03_team02.payload.request.business.AdvertRequest;
import com.project.real_estate_project03_team02.repository.business.CategoryPropertyValueRepository;
import com.project.real_estate_project03_team02.service.helper.CategoryServiceHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * The CategoryPropertyValueService class provides methods to interact with CategoryPropertyValue entities,
 * including retrieving, saving, and updating their values.
 * It serves as a service layer component in the real estate project, specifically focused on business-related categories.
 * This class collaborates with CategoryPropertyValueRepository and CategoryServiceHelper to accomplish its tasks.
 */
@Service
@RequiredArgsConstructor
public class CategoryPropertyValueService {

    /**
     * Repository for accessing and managing CategoryPropertyValue entities.
     */
    private final CategoryPropertyValueRepository categoryPropertyValueRepository;

    /**
     * Helper service for managing category-related operations.
     */
    private final CategoryServiceHelper categoryServiceHelper;

    /**
     * Retrieves the CategoryPropertyValue object corresponding to the given CategoryPropertyKey.
     *
     * @param categoryPropertyKey The CategoryPropertyKey object to search for.
     * @return The CategoryPropertyValue object matching the provided CategoryPropertyKey, if found; otherwise, null.
     */
    public CategoryPropertyValue findByCategoryPropertyKey(CategoryPropertyKey categoryPropertyKey){
        return categoryPropertyValueRepository.findByCategoryPropertyKey(categoryPropertyKey).orElseThrow(()-> new ResourceNotFoundException(ErrorMessages.CATEGORY_PROPERTY_VALUE_NOT_FOUND));
    }

    public void saveCategoryPropertyValues(List<Map<Long, String>> properties, Advert advert) {
        properties.forEach(propertyMap -> {
            propertyMap.forEach((keyId, value) -> {
                CategoryPropertyKey categoryPropertyKey = categoryServiceHelper.findCategoryPropertyKeyById(keyId);

                CategoryPropertyValue categoryPropertyValue = new CategoryPropertyValue();
                categoryPropertyValue.setValue(value);
                categoryPropertyValue.setAdvert(advert);
                categoryPropertyValue.setCategoryPropertyKey(categoryPropertyKey);
                categoryPropertyValueRepository.save(categoryPropertyValue);
            });
        });
    }

    public CategoryPropertyValue findByAdvertId(Advert advert) {

        return categoryPropertyValueRepository.findByAdvert(advert).orElseThrow(()-> new ResourceNotFoundException(String.format(ErrorMessages.CATEGORY_PROPERTY_VALUE_WITH_ADVERT_ID_NOT_FOUND,advert.getId())));
    }
}
