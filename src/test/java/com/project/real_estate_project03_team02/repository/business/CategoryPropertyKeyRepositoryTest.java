package com.project.real_estate_project03_team02.repository.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Category;
import com.project.real_estate_project03_team02.entity.concretes.business.CategoryPropertyKey;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryPropertyKeyRepositoryTest {

    @Mock
    private CategoryPropertyKeyRepository categoryPropertyKeyRepository;
    @Test
    void findAllByCategoryIdShouldReturnListOfCategoryPropertyKeys() {
        Category category = new Category();
        List<CategoryPropertyKey> expectedPropertyKeys = new ArrayList<>();
        expectedPropertyKeys.add(new CategoryPropertyKey());
        expectedPropertyKeys.add(new CategoryPropertyKey());
        when(categoryPropertyKeyRepository.findAllByCategoryId(category)).thenReturn((ArrayList<CategoryPropertyKey>) expectedPropertyKeys);
        List<CategoryPropertyKey> resultPropertyKeys = categoryPropertyKeyRepository.findAllByCategoryId(category);
        assertNotNull(resultPropertyKeys);
        assertEquals(expectedPropertyKeys.size(), resultPropertyKeys.size());
        assertTrue(resultPropertyKeys.containsAll(expectedPropertyKeys));
    }

    @Test
    void findAllByCategoryIdShouldReturnEmptyListForNoPropertyKeys() {
        Category category = new Category();
        when(categoryPropertyKeyRepository.findAllByCategoryId(category)).thenReturn(new ArrayList<>());
        List<CategoryPropertyKey> resultPropertyKeys = categoryPropertyKeyRepository.findAllByCategoryId(category);
        assertNotNull(resultPropertyKeys);
        assertTrue(resultPropertyKeys.isEmpty());
    }
}