package com.project.real_estate_project03_team02.controller.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import com.project.real_estate_project03_team02.payload.messages.SuccessMessages;
import com.project.real_estate_project03_team02.payload.request.business.CategoryPropertyKeyRequest;
import com.project.real_estate_project03_team02.payload.request.business.CategoryRequest;
import com.project.real_estate_project03_team02.payload.response.business.CategoryPropertyKeyResponse;
import com.project.real_estate_project03_team02.payload.response.business.CategoryResponse;
import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.service.business.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.Collections;

class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCategoriesForEverybody() {

        Page<CategoryResponse> expectedPage = mock(Page.class);
        when(categoryService.getAllCategoriesForEverybody(anyString(), anyInt(), anyInt(), anyString(), anyString()))
                .thenReturn(expectedPage);

        Page<CategoryResponse> actualPage = categoryController.getAllCategoriesForEverybody("query", 0, 20, "id", "asc");

        assertEquals(expectedPage, actualPage);
        verify(categoryService).getAllCategoriesForEverybody("query", 0, 20, "id", "asc");
    }
    @Test
    void getCategoryById() {
        Long categoryId = 1L;
        CategoryResponse expectedResponse = new CategoryResponse(); // Create a sample response
        when(categoryService.getCategoryById(categoryId)).thenReturn(expectedResponse);
        CategoryResponse actualResponse = categoryController.getCategoryById(categoryId);
        assertEquals(expectedResponse, actualResponse);
        verify(categoryService).getCategoryById(categoryId);
    }

    @Test
    void getAllCategoriesForManagers() {
        Page<CategoryResponse> expectedPage = mock(Page.class);
        when(categoryService.getAllCategoriesForManagers(anyString(), anyInt(), anyInt(), anyString(), anyString()))
                .thenReturn(expectedPage);
        Page<CategoryResponse> actualPage = categoryController.getAllCategoriesForManagers("query", 0, 20, "category_id", "asc");
        assertEquals(expectedPage, actualPage);
        verify(categoryService).getAllCategoriesForManagers("query", 0, 20, "category_id", "asc");
    }

    @Test
    void createCategory() {
        CategoryRequest request = mock(CategoryRequest.class);
        CategoryResponse categoryResponse = mock(CategoryResponse.class);
        ResponseMessage<CategoryResponse> expectedResponse = new ResponseMessage<>(categoryResponse, SuccessMessages.CATEGORY_SAVED, HttpStatus.CREATED);
        when(categoryService.createCategory(request)).thenReturn(expectedResponse);

        ResponseMessage<CategoryResponse> actualResponse = categoryController.createCategory(request);

        assertEquals(expectedResponse, actualResponse);
        verify(categoryService).createCategory(request);
    }

    @Test
    void updateCategoryById() {

        Long categoryId = 1L;
        CategoryRequest request = new CategoryRequest(); // Create a sample request
        CategoryResponse expectedResponse = new CategoryResponse(); // Create a sample response
        when(categoryService.updateCategoryById(categoryId, request)).thenReturn(expectedResponse);

        CategoryResponse actualResponse = categoryController.updateCategoryById(categoryId, request);

        assertEquals(expectedResponse, actualResponse);
        verify(categoryService).updateCategoryById(categoryId, request);
    }

    @Test
    void deleteCategoryById() {

        Long categoryId = 1L;
        CategoryResponse expectedResponse = mock(CategoryResponse.class);
        when(categoryService.deleteCategoryById(categoryId)).thenReturn(expectedResponse);
        CategoryResponse actualResponse = categoryController.deleteCategoryById(categoryId);

        assertEquals(expectedResponse, actualResponse);
        verify(categoryService).deleteCategoryById(categoryId);
    }

    @Test
    void getCategoryPropertyKeyByCategoryId() {
        when(categoryService.getCategoryPropertyKeyByCategoryId(anyLong())).thenReturn(Collections.emptyList());
        categoryController.getCategoryPropertyKeyByCategoryId(1L);
        verify(categoryService).getCategoryPropertyKeyByCategoryId(1L);
    }

    @Test
    void saveCategoryPropertyKeyByCategoryId() {

        when(categoryService.saveCategoryPropertyKeyByCategoryId(anyLong(), any(CategoryPropertyKeyRequest.class)))
                .thenReturn(mock(CategoryPropertyKeyResponse.class));
        categoryController.saveCategoryPropertyKeyByCategoryId(1L, new CategoryPropertyKeyRequest());

        verify(categoryService).saveCategoryPropertyKeyByCategoryId(1L, new CategoryPropertyKeyRequest());
    }

    @Test
    void updateCategoryPropertyKeyById() {
        when(categoryService.updateCategoryPropertyKeyById(anyLong(), any(CategoryPropertyKeyRequest.class)))
                .thenReturn(mock(CategoryPropertyKeyResponse.class));
        categoryController.updateCategoryPropertyKeyById(1L, new CategoryPropertyKeyRequest());

        verify(categoryService).updateCategoryPropertyKeyById(1L, new CategoryPropertyKeyRequest());
    }

    @Test
    void deleteCategoryPropertyKeyById() {
        when(categoryService.deleteCategoryPropertyKeyById(anyLong())).thenReturn(mock(CategoryPropertyKeyResponse.class));
        categoryController.deleteCategoryPropertyKeyById(1L);
        verify(categoryService).deleteCategoryPropertyKeyById(1L);
    }
}
