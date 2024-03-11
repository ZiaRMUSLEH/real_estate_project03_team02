package com.project.real_estate_project03_team02.service.helper;

import com.project.real_estate_project03_team02.entity.concretes.business.Category;
import com.project.real_estate_project03_team02.entity.concretes.business.CategoryPropertyKey;
import com.project.real_estate_project03_team02.exception.BadRequestException;
import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import com.project.real_estate_project03_team02.repository.business.CategoryPropertyKeyRepository;
import com.project.real_estate_project03_team02.repository.business.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * The CategoryServiceHelper class provides helper methods for accessing and managing category-related data
 * within the real estate project.
 * This class is responsible for interacting with the Category and CategoryPropertyKey entities
 * and their respective repositories.
 */
@Component
@RequiredArgsConstructor
public class CategoryServiceHelper {

    private final CategoryRepository categoryRepository;
    private final CategoryPropertyKeyRepository categoryPropertyKeyRepository;

    /**
     * Retrieves a category by its unique identifier.
     *
     * @param id The ID of the category to retrieve.
     * @return The Category object corresponding to the provided ID.
     * @throws BadRequestException If no category is found with the given ID.
     */
    public Category findCategoryById(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(String.format(ErrorMessages.NO_CATEGORY_WITH_ID, id)));
    }

    /**
     * Retrieves a category property key by its unique identifier.
     *
     * @param id The ID of the category property key to retrieve.
     * @return The CategoryPropertyKey object corresponding to the provided ID.
     * @throws BadRequestException If no category property key is found with the given ID.
     */
    public CategoryPropertyKey findCategoryPropertyKeyById(Long id){
        return categoryPropertyKeyRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(String.format(ErrorMessages.NO_CATEGORY_PROPERTY_KEY_WITH_ID, id)));
    }

    /**
     * Retrieves a list of category property keys associated with a given category.
     *
     * @param category The Category object for which to retrieve property keys.
     * @return A list of CategoryPropertyKey objects associated with the provided category.
     */
    public List<CategoryPropertyKey> findCategoryPropertyKeyByCategoryId(Category category){
        return categoryPropertyKeyRepository.findAllByCategoryId(category);
    }
}
