package com.project.real_estate_project03_team02.repository.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Category;
import com.project.real_estate_project03_team02.service.helper.CategoryServiceHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryRepositoryTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    void findByIsActiveShouldReturnPageOfCategories() {
        boolean isActive = true;
        Pageable pageable = Pageable.unpaged();
        Page<Category> expectedPage = mock(Page.class);
        when(categoryRepository.findByIsActive(isActive, pageable)).thenReturn(expectedPage);
        Page<Category> resultPage = categoryRepository.findByIsActive(isActive, pageable);
        assertNotNull(resultPage);
        assertEquals(expectedPage, resultPage);
    }

    @Test
    void findByTitleContainingShouldReturnPageOfCategories() {
        String query = "search";
        Pageable pageable = Pageable.unpaged();
        Page<Category> expectedPage = mock(Page.class);
        when(categoryRepository.findByTitleContaining(query, pageable)).thenReturn(expectedPage);
        Page<Category> resultPage = categoryRepository.findByTitleContaining(query, pageable);
        assertNotNull(resultPage);
        assertEquals(expectedPage, resultPage);
    }

    @Test
    void findByTitleContainingAndIsActiveShouldReturnPageOfCategories() {
        String query = "search";
        boolean isActive = true;
        Pageable pageable = Pageable.unpaged();
        Page<Category> expectedPage = mock(Page.class);
        when(categoryRepository.findByTitleContainingAndIsActive(query, isActive, pageable)).thenReturn(expectedPage);
        Page<Category> resultPage = categoryRepository.findByTitleContainingAndIsActive(query, isActive, pageable);
        assertNotNull(resultPage);
        assertEquals(expectedPage, resultPage);
    }
}