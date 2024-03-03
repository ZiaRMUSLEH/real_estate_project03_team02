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
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryPropertyKeyRepository categoryPropertyKeyRepository;
    private final AdvertRepository advertRepository;
    private final PageableHelper pageableHelper;
    private final CategoryServiceHelper categoryServiceHelper;
    private final CategoryMapper categoryMapper;
    private final CategoryPropertyKeyMapper categoryPropertyKeyMapper;
    private final SlugGenerator slugGenerator;



    public Page<CategoryResponse> getAllCategoriesForEverybody(String q, int page, int size, String sort, String type) {
        Pageable pageable = pageableHelper.getPageableWithProperties(page, size, sort, type);
        if (q != null && !q.isEmpty()) {
           return categoryRepository.findByTitleContainingAndIsActive(q,true, pageable).map(categoryMapper::mapCategoryToCategoryResponse);

        } else {
            return categoryRepository.findByIsActive(true,pageable).map(categoryMapper::mapCategoryToCategoryResponse);
        }
    }
    public Page<CategoryResponse> getAllCategoriesForManagers(String q, int page, int size, String sort, String type) {
        Pageable pageable = pageableHelper.getPageableWithProperties(page, size, sort, type);
        if (q != null && !q.isEmpty()) {
            return categoryRepository.findByTitleContaining(q, pageable).map(categoryMapper::mapCategoryToCategoryResponse);

        } else {
            return categoryRepository.findAll(pageable).map(categoryMapper::mapCategoryToCategoryResponse);
        }
    }

    public CategoryResponse getCategoryById(Long id) {
        Category categoryById=categoryServiceHelper.findCategoryById(id);
        return categoryMapper.mapCategoryToCategoryResponse(categoryById);


    }

    public ResponseMessage<CategoryResponse> createCategory(CategoryRequest categoryRequest) {
            Category newCategory = categoryMapper.mapCategoryRequestToCategory(categoryRequest);
            newCategory.setActive(true);
            newCategory.setSeq(0);
            newCategory.setSlug(slugGenerator.generateSlug(categoryRequest.getTitle()));
            newCategory.setCreatedAt(LocalDateTime.now());
            Category savedCategory = categoryRepository.save(newCategory);
            return ResponseMessage.<CategoryResponse>builder()
                    .message(SuccessMessages.CATEGORY_SAVED)
                    .httpStatus(HttpStatus.CREATED)
                    .object(categoryMapper.mapCategoryToCategoryResponse(savedCategory))
                    .build();


    }


    public CategoryResponse updateCategoryById(Long id, CategoryRequest categoryRequest) {
        Category categoryById=categoryServiceHelper.findCategoryById(id);
        Category newCategory = categoryMapper.mapCategoryRequestToCategory(categoryRequest);
       if (categoryById.isBuiltIn()){
           throw new BadRequestException(String.format(ErrorMessages.CATEGORY_IS_BUILT_IN,id));
       }
        newCategory.setId(categoryById.getId());
        newCategory.setCreatedAt(categoryById.getCreatedAt());
        newCategory.setTitle(newCategory.getTitle());
        newCategory.setIcon(newCategory.getIcon());
        newCategory.setSeq(categoryById.getSeq());
        newCategory.setSlug(slugGenerator.generateSlug(newCategory.getTitle()));
        newCategory.setActive(categoryById.isActive());
        newCategory.setUpdatedAt(LocalDateTime.now());
        Category savedCategory=categoryRepository.save(newCategory);
        return categoryMapper.mapCategoryToCategoryResponse(savedCategory);
    }

    public CategoryResponse deleteCategoryById(Long id) {
        Category categoryById=categoryServiceHelper.findCategoryById(id);
        Advert advert =advertRepository.findByCategoryId(categoryById).orElse(null);
        if (advert != null){
            throw new BadRequestException(String.format(ErrorMessages.CATEGORY_WITH_ID_HAVE_ADVERT,id));
        }
        if (categoryById.isBuiltIn()){
            throw new BadRequestException(String.format(ErrorMessages.CATEGORY_IS_BUILT_IN,id));

        }
        categoryRepository.delete(categoryById);
        return categoryMapper.mapCategoryToCategoryResponse(categoryById);

    }

    public List<CategoryPropertyKeyResponse> getCategoryPropertyKeyByCategoryId(Long categoryId) {
        Category category=categoryServiceHelper.findCategoryById(categoryId);
       ArrayList<CategoryPropertyKey> categoryPropertyKeyList= new ArrayList<>(categoryPropertyKeyRepository.findAllByCategoryId(category));
        return categoryPropertyKeyMapper.mapCategoryPropertyKeyListToResponseList(categoryPropertyKeyList);
    }


    public CategoryPropertyKeyResponse saveCategoryPropertyKeyByCategoryId(Long categoryId, CategoryPropertyKeyRequest categoryPropertyKeyRequest) {
        Category category=categoryServiceHelper.findCategoryById(categoryId);
        CategoryPropertyKey newCategoryPropertyKey= categoryPropertyKeyMapper.mapCategoryPropertyKeyRequestToCategoryPropertyKey(categoryPropertyKeyRequest);
        newCategoryPropertyKey.setCategoryId(category);
        CategoryPropertyKey savedCategoryPropertyKey =categoryPropertyKeyRepository.save(newCategoryPropertyKey);
        return categoryPropertyKeyMapper.mapCategoryPropertyKeyToCategoryPropertyKeyResponse(savedCategoryPropertyKey);
    }

    public CategoryPropertyKeyResponse updateCategoryPropertyKeyById(Long id, CategoryPropertyKeyRequest categoryPropertyKeyRequest) {
       CategoryPropertyKey newCategoryPropertyKey= categoryServiceHelper.findCategoryPropertyKeyById(id);
        if(newCategoryPropertyKey.isBuiltIn()){
            throw new BadRequestException(String.format(ErrorMessages.CATEGORY_PROPERTY_KEY_IS_BUILT_IN,id));
        }
       newCategoryPropertyKey.setId(id);
       newCategoryPropertyKey.setName(categoryPropertyKeyRequest.getName());
       newCategoryPropertyKey.setCategoryId(newCategoryPropertyKey.getCategoryId());
       CategoryPropertyKey savedCategoryPropertyKey=categoryPropertyKeyRepository.save(newCategoryPropertyKey);
        return categoryPropertyKeyMapper.mapCategoryPropertyKeyToCategoryPropertyKeyResponse(savedCategoryPropertyKey);
    }

    public CategoryPropertyKeyResponse deleteCategoryPropertyKeyById(Long id) {
       CategoryPropertyKey categoryPropertyKey= categoryServiceHelper.findCategoryPropertyKeyById(id);
        if(categoryPropertyKey.isBuiltIn()){
            throw new BadRequestException(String.format(ErrorMessages.CATEGORY_PROPERTY_KEY_IS_BUILT_IN,id));
        }
        categoryPropertyKeyRepository.delete(categoryPropertyKey);

        return categoryPropertyKeyMapper.mapCategoryPropertyKeyToCategoryPropertyKeyResponse(categoryPropertyKey);
    }
}
