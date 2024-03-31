package com.project.real_estate_project03_team02.service.business;

import com.project.real_estate_project03_team02.entity.concretes.business.CategoryPropertyKey;
import com.project.real_estate_project03_team02.entity.concretes.business.CategoryPropertyValue;
import com.project.real_estate_project03_team02.payload.request.business.AdvertRequest;
import com.project.real_estate_project03_team02.repository.business.CategoryPropertyValueRepository;
import com.project.real_estate_project03_team02.service.helper.CategoryServiceHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


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
        return categoryPropertyValueRepository.findByCategoryPropertyKey(categoryPropertyKey);
    }

    /**
     * Saves CategoryPropertyValue entities based on the properties provided in the AdvertRequest.
     * Iterates through the properties, retrieves corresponding CategoryPropertyKey, finds the existing CategoryPropertyValue
     * for each key, updates its value, and then saves the updated CategoryPropertyValue.
     *
     * @param advertRequest The AdvertRequest containing properties to be saved.
     */
//    public void saveCategoryPropertyValue(AdvertRequest advertRequest) {
//        // Iterate through each property in the advert request
//        advertRequest.getProperties().forEach(propertyMap -> {
//            // Iterate through each key-value pair in the property map
//            propertyMap.forEach((keyId, value) -> {
//                // Find the CategoryPropertyKey object using the keyId
//                CategoryPropertyKey categoryPropertyKey = categoryServiceHelper.findCategoryPropertyKeyById(keyId);
//
//                CategoryPropertyValue categoryPropertyValue = new CategoryPropertyValue();
//
//                // Set the value of the CategoryPropertyValue to the new value
//                categoryPropertyValue.setValue(value);
//                categoryPropertyValue.setAdvert();
//                categoryPropertyValue.setCategoryPropertyKey(categoryPropertyKey);
//
//                // Save the updated CategoryPropertyValue
//                save(categoryPropertyValue);
//            });
//        });
//    }

    /**
     * Saves a CategoryPropertyValue object to the database.
     *
     * @param categoryPropertyValue The CategoryPropertyValue object to be saved.
     */
    public void save(CategoryPropertyValue categoryPropertyValue) {
        categoryPropertyValueRepository.save(categoryPropertyValue);
    }
}
