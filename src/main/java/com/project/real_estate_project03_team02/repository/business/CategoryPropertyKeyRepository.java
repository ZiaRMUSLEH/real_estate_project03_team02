package com.project.real_estate_project03_team02.repository.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Category;
import com.project.real_estate_project03_team02.entity.concretes.business.CategoryPropertyKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface CategoryPropertyKeyRepository extends JpaRepository<CategoryPropertyKey,Long> {
    ArrayList<CategoryPropertyKey> findAllByCategoryId(Category category);

}
