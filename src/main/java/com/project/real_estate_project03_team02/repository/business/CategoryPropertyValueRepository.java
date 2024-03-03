package com.project.real_estate_project03_team02.repository.business;


import com.project.real_estate_project03_team02.entity.concretes.business.CategoryPropertyKey;
import com.project.real_estate_project03_team02.entity.concretes.business.CategoryPropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface CategoryPropertyValueRepository extends JpaRepository<CategoryPropertyValue,Long> {
    CategoryPropertyValue findByCategoryPropertyKey(CategoryPropertyKey categoryPropertyKey);


}
