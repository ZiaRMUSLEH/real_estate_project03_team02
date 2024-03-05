package com.project.real_estate_project03_team02.controller.business;


import com.project.real_estate_project03_team02.payload.request.business.CategoryPropertyKeyRequest;
import com.project.real_estate_project03_team02.payload.request.business.CategoryRequest;
import com.project.real_estate_project03_team02.payload.response.business.CategoryPropertyKeyResponse;
import com.project.real_estate_project03_team02.payload.response.business.CategoryResponse;
import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.service.business.CategoryService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("categories")
@RequiredArgsConstructor
public class CategoryController {




    private final CategoryService categoryService;

    @GetMapping("")
    public Page<CategoryResponse> getAllCategoriesForEverybody(@RequestParam(required = false) String q,
                                                   @RequestParam(value = "page", defaultValue = "0") int page,
                                                   @RequestParam(value = "size", defaultValue = "20") int size,
                                                   @RequestParam(value = "sort", defaultValue = "id") String sort,
                                                   @RequestParam(value = "type", defaultValue = "asc") String type) {
        return categoryService.getAllCategoriesForEverybody( q,page, size, sort, type);
    }
    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }
    @GetMapping("/admin")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public Page<CategoryResponse> getAllCategoriesForManagers(@RequestParam(required = false) String q,
                                                   @RequestParam(value = "page", defaultValue = "0") int page,
                                                   @RequestParam(value = "size", defaultValue = "20") int size,
                                                   @RequestParam(value = "sort", defaultValue = "category_id") String sort,
                                                   @RequestParam(value = "type", defaultValue = "asc") String type) {
        return categoryService.getAllCategoriesForManagers( q,page, size, sort, type);
    }
    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseMessage<CategoryResponse> createCategory(@RequestBody @Valid CategoryRequest categoryRequest) {
        return categoryService.createCategory(categoryRequest);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public CategoryResponse updateCategoryById(@PathVariable Long id,@RequestBody @Valid CategoryRequest categoryRequest) {
        return categoryService.updateCategoryById(id,categoryRequest);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public CategoryResponse deleteCategoryById(@PathVariable Long id) {
        return categoryService.deleteCategoryById(id);
    }
    @GetMapping("/{id}/properties")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public List<CategoryPropertyKeyResponse> getCategoryPropertyKeyByCategoryId(@PathVariable Long id) {
        return categoryService.getCategoryPropertyKeyByCategoryId(id);
    }
    @PostMapping("/{id}/properties")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public CategoryPropertyKeyResponse saveCategoryPropertyKeyByCategoryId(@PathVariable Long id,@RequestBody @Valid CategoryPropertyKeyRequest categoryPropertyKeyRequest) {
        return categoryService.saveCategoryPropertyKeyByCategoryId(id,categoryPropertyKeyRequest);
    }
    @PutMapping("/properties/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public CategoryPropertyKeyResponse updateCategoryPropertyKeyById(@PathVariable Long id,@RequestBody @Valid CategoryPropertyKeyRequest categoryPropertyKeyRequest) {
        return categoryService.updateCategoryPropertyKeyById(id,categoryPropertyKeyRequest);
    }
    @DeleteMapping("/properties/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public CategoryPropertyKeyResponse deleteCategoryPropertyKeyById(@PathVariable Long id) {
        return categoryService.deleteCategoryPropertyKeyById(id);
    }

}
