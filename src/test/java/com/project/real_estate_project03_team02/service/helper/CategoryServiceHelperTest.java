package com.project.real_estate_project03_team02.service.helper;

import com.project.real_estate_project03_team02.entity.concretes.business.Category;
import com.project.real_estate_project03_team02.entity.concretes.business.CategoryPropertyKey;
import com.project.real_estate_project03_team02.exception.BadRequestException;
import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import com.project.real_estate_project03_team02.repository.business.CategoryPropertyKeyRepository;
import com.project.real_estate_project03_team02.repository.business.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceHelperTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryPropertyKeyRepository categoryPropertyKeyRepository;

    @InjectMocks
    private CategoryServiceHelper categoryServiceHelper;

    @Test
    void findCategoryByIdShouldReturnCategoryWhenExists() {
        // Given
        Long categoryId = 1L;
        Category expectedCategory = new Category();
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(expectedCategory));

        // When
        Category resultCategory = categoryServiceHelper.findCategoryById(categoryId);

        // Then
        assertNotNull(resultCategory);
        assertEquals(expectedCategory, resultCategory);
    }

    @Test
    void findCategoryByIdShouldThrowBadRequestExceptionWhenNotFound() {
        // Given
        Long categoryId = 1L;
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        // When and Then
        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> categoryServiceHelper.findCategoryById(categoryId));
        assertEquals(String.format(ErrorMessages.NO_CATEGORY_WITH_ID, categoryId), exception.getMessage());
    }

    @Test
    void findCategoryPropertyKeyByIdShouldReturnCategoryPropertyKeyWhenExists() {
        // Given
        Long propertyKeyId = 1L;
        CategoryPropertyKey expectedPropertyKey = new CategoryPropertyKey();
        when(categoryPropertyKeyRepository.findById(propertyKeyId)).thenReturn(Optional.of(expectedPropertyKey));

        // When
        CategoryPropertyKey resultPropertyKey = categoryServiceHelper.findCategoryPropertyKeyById(propertyKeyId);

        // Then
        assertNotNull(resultPropertyKey);
        assertEquals(expectedPropertyKey, resultPropertyKey);
    }

    @Test
    void findCategoryPropertyKeyByIdShouldThrowBadRequestExceptionWhenNotFound() {
        // Given
        Long propertyKeyId = 1L;
        when(categoryPropertyKeyRepository.findById(propertyKeyId)).thenReturn(Optional.empty());

        // When and Then
        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> categoryServiceHelper.findCategoryPropertyKeyById(propertyKeyId));
        assertEquals(String.format(ErrorMessages.NO_CATEGORY_PROPERTY_KEY_WITH_ID, propertyKeyId), exception.getMessage());
    }
}