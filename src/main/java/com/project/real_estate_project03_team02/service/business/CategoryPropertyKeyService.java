package com.project.real_estate_project03_team02.service.business;

import com.project.real_estate_project03_team02.entity.concretes.business.CategoryPropertyKey;
import com.project.real_estate_project03_team02.repository.business.CategoryPropertyKeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryPropertyKeyService {


    private final CategoryPropertyKeyRepository categoryPropertyKeyRepository;

    public List<CategoryPropertyKey> getAll() {
      return   categoryPropertyKeyRepository.findAll();
    }

    public void save(CategoryPropertyKey categoryPropertyKey) {
        categoryPropertyKeyRepository.save(categoryPropertyKey);
    }
}
