package com.project.real_estate_project03_team02.service.business;
import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.entity.concretes.business.Category;
import com.project.real_estate_project03_team02.entity.concretes.business.CategoryPropertyKey;
import com.project.real_estate_project03_team02.exception.BadRequestException;
import com.project.real_estate_project03_team02.payload.mappers.business.CategoryMapper;
import com.project.real_estate_project03_team02.payload.mappers.business.CategoryPropertyKeyMapper;
import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import com.project.real_estate_project03_team02.payload.messages.SuccessMessages;
import com.project.real_estate_project03_team02.payload.request.business.CategoryPropertyKeyRequest;
import com.project.real_estate_project03_team02.payload.request.business.CategoryRequest;
import com.project.real_estate_project03_team02.payload.response.business.CategoryPropertyKeyResponse;
import com.project.real_estate_project03_team02.payload.response.business.CategoryResponse;
import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.repository.business.AdvertRepository;
import com.project.real_estate_project03_team02.repository.business.CategoryPropertyKeyRepository;
import com.project.real_estate_project03_team02.repository.business.CategoryRepository;
import com.project.real_estate_project03_team02.service.helper.CategoryServiceHelper;
import com.project.real_estate_project03_team02.service.helper.PageableHelper;
import com.project.real_estate_project03_team02.service.helper.SlugGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
class CategoryServiceTest {

        @Mock
        private  CategoryRepository categoryRepository;
        @Mock
        private CategoryPropertyKeyRepository categoryPropertyKeyRepository;
        @Mock
        private CategoryPropertyKeyMapper categoryPropertyKeyMapper;
        @Mock
        private SlugGenerator slugGenerator;
        @Mock
        private AdvertRepository advertRepository;
        @Mock
        private  PageableHelper pageableHelper;
        @Mock
        private  CategoryMapper categoryMapper;
        @InjectMocks
        private  CategoryService categoryService;
        @Mock
        private CategoryServiceHelper categoryServiceHelper;
        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        int page = 0;
        int size = 10;
        String sort = "title";
        String type = "asc";
        @Test
        void testGetAllCategoriesForEverybody_WithQuery() {
            String query = "test";
            Pageable pageable = Pageable.ofSize(size).withPage(page);
            Page<Category> mockCategoryPage = new PageImpl<>(Collections.emptyList());
            when(pageableHelper.getPageableWithProperties(page, size, sort, type)).thenReturn(pageable);
            when(categoryRepository.findByTitleContainingAndIsActive(anyString(), anyBoolean(), any(Pageable.class))).thenReturn(mockCategoryPage);

            Page<CategoryResponse> result = categoryService.getAllCategoriesForEverybody(query, page, size, sort, type);

            verify(pageableHelper).getPageableWithProperties(page, size, sort, type);
            verify(categoryRepository).findByTitleContainingAndIsActive(query, true, pageable);
            assertEquals(mockCategoryPage.map(categoryMapper::mapCategoryToCategoryResponse), result);
        }

        @Test
        void testGetAllCategoriesForEverybody_WithoutQuery() {
            String query = null;

            Pageable pageable = Pageable.ofSize(size).withPage(page);
            Page<Category> mockCategoryPage = new PageImpl<>(Collections.emptyList());
            when(pageableHelper.getPageableWithProperties(page, size, sort, type)).thenReturn(pageable);
            when(categoryRepository.findByIsActive(anyBoolean(), any(Pageable.class))).thenReturn(mockCategoryPage);

            Page<CategoryResponse> result = categoryService.getAllCategoriesForEverybody(query, page, size, sort, type);

            verify(pageableHelper).getPageableWithProperties(page, size, sort, type);
            verify(categoryRepository).findByIsActive(true, pageable);
            assertEquals(mockCategoryPage.map(categoryMapper::mapCategoryToCategoryResponse), result);
        }
        @Test
        void testGetAllCategoriesForManagers_WithQuery() {
            String query = "test";

            Pageable pageable = Pageable.ofSize(size).withPage(page);
            Page<Category> mockCategoryPage = new PageImpl<>(Collections.emptyList());
            when(pageableHelper.getPageableWithProperties(page, size, sort, type)).thenReturn(pageable);
            when(categoryRepository.findByTitleContaining(query, pageable)).thenReturn(mockCategoryPage);

            Page<CategoryResponse> result = categoryService.getAllCategoriesForManagers(query, page, size, sort, type);

            verify(pageableHelper).getPageableWithProperties(page, size, sort, type);
            verify(categoryRepository).findByTitleContaining(query, pageable);
            assertEquals(mockCategoryPage.map(categoryMapper::mapCategoryToCategoryResponse), result);
        }

        @Test
        void testGetAllCategoriesForManagers_WithoutQuery() {
            String query = null;
            Pageable pageable = Pageable.ofSize(size).withPage(page);
            Page<Category> mockCategoryPage = new PageImpl<>(Collections.emptyList());
            when(pageableHelper.getPageableWithProperties(page, size, sort, type)).thenReturn(pageable);
            when(categoryRepository.findAll(pageable)).thenReturn(mockCategoryPage);

            Page<CategoryResponse> result = categoryService.getAllCategoriesForManagers(query, page, size, sort, type);

            verify(pageableHelper).getPageableWithProperties(page, size, sort, type);
            verify(categoryRepository).findAll(pageable);
            assertEquals(mockCategoryPage.map(categoryMapper::mapCategoryToCategoryResponse), result);
        }
        @Test
        void testGetCategoryById() {
            long categoryId = 1L;
            Category category = new Category();
            category.setId(categoryId);
            when(categoryServiceHelper.findCategoryById(categoryId)).thenReturn(category);
            CategoryResponse expectedResponse = new CategoryResponse();
            expectedResponse.setId(categoryId);
            when(categoryMapper.mapCategoryToCategoryResponse(category)).thenReturn(expectedResponse);

            CategoryResponse result = categoryService.getCategoryById(categoryId);

            assertEquals(expectedResponse, result);
            assertEquals(categoryId, result.getId());
        }

    @Test
    void testCreateCategory() {
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setTitle("Test Category");
        Category newCategory = new Category();
        newCategory.setId(1L);
        newCategory.setTitle(categoryRequest.getTitle());
        newCategory.setActive(true);
        newCategory.setSeq(0);
        String slug = "test-category";
        newCategory.setSlug(slug);
        LocalDateTime now = LocalDateTime.now();
        newCategory.setCreatedAt(now);
        CategoryResponse expectedResponse = new CategoryResponse();
        expectedResponse.setId(newCategory.getId());
        expectedResponse.setTitle(newCategory.getTitle());
        expectedResponse.setActive(newCategory.isActive());
        expectedResponse.setSeq(newCategory.getSeq());
        expectedResponse.setSlug(newCategory.getSlug());
        when(categoryMapper.mapCategoryRequestToCategory(categoryRequest)).thenReturn(newCategory);
        when(slugGenerator.generateSlug(categoryRequest.getTitle())).thenReturn(slug);
        when(categoryRepository.save(any(Category.class))).thenReturn(newCategory);
        when(categoryMapper.mapCategoryToCategoryResponse(newCategory)).thenReturn(expectedResponse);


        ResponseMessage<CategoryResponse> result = categoryService.createCategory(categoryRequest);

        assertEquals(SuccessMessages.CATEGORY_SAVED, result.getMessage());
        assertEquals(201, result.getHttpStatus().value());
        assertEquals(expectedResponse, result.getObject());
    }
    @Test
    void testUpdateCategoryById_WhenCategoryIsBuiltIn() {
        long categoryId = 1L;
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setTitle("Test");
        categoryRequest.setIcon("Icon");

        Category categoryById = new Category();
        categoryById.setId(categoryId);
        categoryById.setBuiltIn(true);

        when(categoryServiceHelper.findCategoryById(categoryId)).thenReturn(categoryById);

        BadRequestException exception = assertThrows(BadRequestException.class, () -> categoryService.updateCategoryById(categoryId, categoryRequest));


        assertEquals(String.format(ErrorMessages.CATEGORY_IS_BUILT_IN, categoryId), exception.getMessage());
    }
    @Test
     void testUpdateCategoryById_NotBuiltIn_Success() {
        Long id = 1L;
        CategoryRequest categoryRequest = CategoryRequest.builder()
                .title("New Title")
                .icon("New Icon")
                .build();

        Category categoryById = Category.builder()
                .id(id)
                .title("Existing Title")
                .icon("Existing Icon")
                .seq(2)
                .slug("existing-slug")
                .builtIn(false)
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Category newCategory = Category.builder()
                .title("Updated Title")
                .icon("Updated Icon")
                .seq(3)
                .slug("updated-slug")
                .builtIn(false)
                .isActive(true)
                .createdAt(categoryById.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build();

        Category savedCategory = Category.builder()
                .id(1L)
                .title("Updated Title")
                .icon("Updated Icon")
                .seq(3)
                .slug("updated-slug")
                .builtIn(false)
                .isActive(true)
                .createdAt(categoryById.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build();

        CategoryResponse expectedResponse = CategoryResponse.builder()
                .id(savedCategory.getId())
                .title(savedCategory.getTitle())
                .icon(savedCategory.getIcon())
                .seq(savedCategory.getSeq())
                .slug(savedCategory.getSlug())
                .isActive(savedCategory.isActive())
                .build();
        when(categoryServiceHelper.findCategoryById(id)).thenReturn(categoryById);
        when(categoryMapper.mapCategoryRequestToCategory(categoryRequest)).thenReturn(newCategory);
        when(slugGenerator.generateSlug(anyString())).thenReturn("generated-slug");
        when(categoryRepository.save(newCategory)).thenReturn(savedCategory);
        when(categoryMapper.mapCategoryToCategoryResponse(savedCategory)).thenReturn(expectedResponse);

        CategoryResponse actualResponse = categoryService.updateCategoryById(id, categoryRequest);

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
        verify(categoryRepository, times(1)).save(newCategory);
    }

    @Test
    void testGetCountCategory() {
        when(categoryRepository.count()).thenReturn(10L);
        long count = categoryService.getCountCategory();
        verify(categoryRepository, times(1)).count();
        assertEquals(10L, count);
    }
    @Test
    void testDeleteCategoryById_Success() {
        Long categoryId = 1L;
        Category categoryById = new Category();
        categoryById.setId(categoryId);
        categoryById.setBuiltIn(false);
        when(categoryServiceHelper.findCategoryById(categoryId)).thenReturn(categoryById);
        when(advertRepository.findByCategoryId(categoryById)).thenReturn(Optional.empty());
        when(categoryMapper.mapCategoryToCategoryResponse(categoryById)).thenReturn(new CategoryResponse());

        CategoryResponse response = categoryService.deleteCategoryById(categoryId);

        verify(categoryServiceHelper, times(1)).findCategoryById(categoryId);
        verify(advertRepository, times(1)).findByCategoryId(categoryById);
        verify(categoryRepository, times(1)).delete(categoryById);
        verify(categoryMapper, times(1)).mapCategoryToCategoryResponse(categoryById);
        assertNotNull(response);
    }

    @Test
    void testDeleteCategoryById_CategoryHasAdvert() {
        Long categoryId = 1L;
        Category categoryById = new Category();
        categoryById.setId(categoryId);
        categoryById.setBuiltIn(false);

        when(categoryServiceHelper.findCategoryById(categoryId)).thenReturn(categoryById);
        when(advertRepository.findByCategoryId(categoryById)).thenReturn(Optional.of(new Advert()));


        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            categoryService.deleteCategoryById(categoryId);
        });
        assertEquals(String.format(ErrorMessages.CATEGORY_WITH_ID_HAVE_ADVERT, categoryId), exception.getMessage());


        verify(categoryServiceHelper, times(1)).findCategoryById(categoryId);
        verify(advertRepository, times(1)).findByCategoryId(categoryById);
        verify(categoryRepository, never()).delete(any());
        verify(categoryMapper, never()).mapCategoryToCategoryResponse(any());
    }

    @Test
    void testDeleteCategoryById_CategoryIsBuiltIn() {
        Long categoryId = 1L;
        Category categoryById = new Category();
        categoryById.setId(categoryId);
        categoryById.setBuiltIn(true);

        when(categoryServiceHelper.findCategoryById(categoryId)).thenReturn(categoryById);

        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            categoryService.deleteCategoryById(categoryById.getId());
        });
        assertEquals(String.format(ErrorMessages.CATEGORY_IS_BUILT_IN, categoryId), exception.getMessage());

        verify(categoryServiceHelper, times(1)).findCategoryById(categoryId);
        verify(advertRepository, never()).findByCategoryId(any());
        verify(categoryRepository, never()).delete(any());
        verify(categoryMapper, never()).mapCategoryToCategoryResponse(any());
    }

    @Test
    void testGetCategoryPropertyKeyByCategoryId() {
        Long categoryId = 1L;
        Category category = new Category();
        category.setId(categoryId);

        CategoryPropertyKey categoryPropertyKey1 = new CategoryPropertyKey();
        categoryPropertyKey1.setId(1L);
        categoryPropertyKey1.setCategoryId(category);

        CategoryPropertyKey categoryPropertyKey2 = new CategoryPropertyKey();
        categoryPropertyKey2.setId(2L);
        categoryPropertyKey2.setCategoryId(category);

        List<CategoryPropertyKey> categoryPropertyKeyList = List.of(categoryPropertyKey1, categoryPropertyKey2);
        List<CategoryPropertyKeyResponse> expectedResponseList = categoryPropertyKeyMapper.mapCategoryPropertyKeyListToResponseList(categoryPropertyKeyList);

        when(categoryServiceHelper.findCategoryById(categoryId)).thenReturn(category);
        when(categoryPropertyKeyRepository.findAllByCategoryId(category)).thenReturn(categoryPropertyKeyList);
        when(categoryPropertyKeyMapper.mapCategoryPropertyKeyListToResponseList(categoryPropertyKeyList)).thenReturn(expectedResponseList);

        List<CategoryPropertyKeyResponse> actualResponseList = categoryService.getCategoryPropertyKeyByCategoryId(categoryId);

        assertEquals(expectedResponseList, actualResponseList);
        verify(categoryServiceHelper, times(1)).findCategoryById(categoryId);
        verify(categoryPropertyKeyRepository, times(1)).findAllByCategoryId(category);
        verify(categoryPropertyKeyMapper, times(2)).mapCategoryPropertyKeyListToResponseList(categoryPropertyKeyList);
    }
    @Test
    public void testSaveCategoryPropertyKeyByCategoryId() {
        Long categoryId = 1L;
        CategoryPropertyKeyRequest request = new CategoryPropertyKeyRequest("TestName");
        Category category = Category.builder()
                .id(categoryId)
                .title("Title")
                .icon("Icon")
                .seq(3)
                .slug("title")
                .builtIn(false)
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .build();
        CategoryPropertyKey newCategoryPropertyKey = new CategoryPropertyKey();
        CategoryPropertyKeyResponse expectedResponse = new CategoryPropertyKeyResponse(categoryId,"TestName",categoryMapper.mapCategoryToCategoryResponse(category));

        when(categoryServiceHelper.findCategoryById(categoryId)).thenReturn(category);
        when(categoryPropertyKeyMapper.mapCategoryPropertyKeyRequestToCategoryPropertyKey(request)).thenReturn(newCategoryPropertyKey);
        when(categoryPropertyKeyRepository.save(newCategoryPropertyKey)).thenReturn(newCategoryPropertyKey);
        when(categoryPropertyKeyMapper.mapCategoryPropertyKeyToCategoryPropertyKeyResponse(newCategoryPropertyKey)).thenReturn(expectedResponse);

        CategoryPropertyKeyResponse actualResponse = categoryService.saveCategoryPropertyKeyByCategoryId(categoryId, request);

        verify(categoryServiceHelper).findCategoryById(categoryId);
        verify(categoryPropertyKeyMapper).mapCategoryPropertyKeyRequestToCategoryPropertyKey(request);
        verify(categoryPropertyKeyRepository).save(newCategoryPropertyKey);
        verify(categoryPropertyKeyMapper).mapCategoryPropertyKeyToCategoryPropertyKeyResponse(newCategoryPropertyKey);
        assertEquals(expectedResponse, actualResponse);
    }
    @Test
    void testUpdateCategoryPropertyKeyById() {
        Long id = 1L;
        CategoryPropertyKeyRequest request = new CategoryPropertyKeyRequest("Updated Name");
        Category category = Category.builder()
                .id(id)
                .title("Title")
                .icon("Icon")
                .seq(3)
                .slug("title")
                .builtIn(false)
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .build();
        CategoryResponse categoryResponse=categoryMapper.mapCategoryToCategoryResponse(category);

        CategoryPropertyKey existingPropertyKey = new CategoryPropertyKey();
        existingPropertyKey.setId(id);
        existingPropertyKey.setName("Old Name");
        existingPropertyKey.setBuiltIn(false);
        existingPropertyKey.setCategoryId(category);

        CategoryPropertyKey updatedPropertyKey = new CategoryPropertyKey();
        updatedPropertyKey.setId(id);
        updatedPropertyKey.setName("Updated Name");
        updatedPropertyKey.setBuiltIn(false);
        updatedPropertyKey.setCategoryId(category);

        when(categoryServiceHelper.findCategoryPropertyKeyById(id)).thenReturn(existingPropertyKey);
        when(categoryPropertyKeyRepository.save(existingPropertyKey)).thenReturn(updatedPropertyKey);
        when(categoryPropertyKeyMapper.mapCategoryPropertyKeyToCategoryPropertyKeyResponse(updatedPropertyKey))
                .thenReturn(new CategoryPropertyKeyResponse(1L,"Updated Name",categoryResponse));

        CategoryPropertyKeyResponse response = categoryService.updateCategoryPropertyKeyById(id, request);

        assertEquals(updatedPropertyKey.getId(), response.getId());
        assertEquals(updatedPropertyKey.getName(), response.getName());
        verify(categoryServiceHelper, times(1)).findCategoryPropertyKeyById(id);
        verify(categoryPropertyKeyRepository, times(1)).save(any());
        verify(categoryPropertyKeyMapper, times(1)).mapCategoryPropertyKeyToCategoryPropertyKeyResponse(updatedPropertyKey);
    }

    @Test
    void testUpdateCategoryPropertyKeyById_BuiltIn() {
        Long id = 1L;
        CategoryPropertyKeyRequest request = new CategoryPropertyKeyRequest();
        request.setName("Updated Name");

        CategoryPropertyKey existingPropertyKey = new CategoryPropertyKey();
        existingPropertyKey.setId(id);
        existingPropertyKey.setName("Old Name");
        existingPropertyKey.setBuiltIn(true);

        when(categoryServiceHelper.findCategoryPropertyKeyById(id)).thenReturn(existingPropertyKey);

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> categoryService.updateCategoryPropertyKeyById(id, request));
        assertEquals(String.format(ErrorMessages.CATEGORY_PROPERTY_KEY_IS_BUILT_IN, id), exception.getMessage());
        verify(categoryServiceHelper, times(1)).findCategoryPropertyKeyById(id);
        verify(categoryPropertyKeyRepository, never()).save(any());
        verify(categoryPropertyKeyMapper, never()).mapCategoryPropertyKeyToCategoryPropertyKeyResponse(any());
    }
    @Test
    void deleteCategoryPropertyKeyById_NotBuiltIn_Success() {
        Long id = 1L;
        Category category = Category.builder()
                .id(id)
                .title("Title")
                .icon("Icon")
                .seq(3)
                .slug("title")
                .builtIn(false)
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .build();
        CategoryPropertyKey categoryPropertyKey = new CategoryPropertyKey();
        categoryPropertyKey.setId(id);
        categoryPropertyKey.setName("For delete");
        categoryPropertyKey.setCategoryId(category);
        categoryPropertyKey.setBuiltIn(false);

        when(categoryServiceHelper.findCategoryPropertyKeyById(id)).thenReturn(categoryPropertyKey);

        categoryService.deleteCategoryPropertyKeyById(id);

        verify(categoryPropertyKeyRepository).delete(categoryPropertyKey);
        verify(categoryPropertyKeyMapper).mapCategoryPropertyKeyToCategoryPropertyKeyResponse(categoryPropertyKey);
    }

    @Test
    void deleteCategoryPropertyKeyById_BuiltIn_ExceptionThrown() {
        Long id = 1L;
        Category category = Category.builder()
                .id(id)
                .title("Title")
                .icon("Icon")
                .seq(3)
                .slug("title")
                .builtIn(false)
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .build();
        CategoryPropertyKey categoryPropertyKey = new CategoryPropertyKey();
        categoryPropertyKey.setId(id);
        categoryPropertyKey.setName("For delete");
        categoryPropertyKey.setCategoryId(category);
        categoryPropertyKey.setBuiltIn(true);
        when(categoryServiceHelper.findCategoryPropertyKeyById(id)).thenReturn(categoryPropertyKey);


        BadRequestException exception = org.junit.jupiter.api.Assertions.assertThrows(BadRequestException.class, () -> {
            categoryService.deleteCategoryPropertyKeyById(id);
        });
        org.junit.jupiter.api.Assertions.assertEquals(String.format(ErrorMessages.CATEGORY_PROPERTY_KEY_IS_BUILT_IN, id), exception.getMessage());

        verify(categoryPropertyKeyRepository, never()).delete(any());
        verify(categoryPropertyKeyMapper, never()).mapCategoryPropertyKeyToCategoryPropertyKeyResponse(any());
    }



}
