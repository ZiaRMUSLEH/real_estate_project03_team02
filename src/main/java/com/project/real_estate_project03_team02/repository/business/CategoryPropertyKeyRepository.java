package com.project.real_estate_project03_team02.repository.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Category;
import com.project.real_estate_project03_team02.entity.concretes.business.CategoryPropertyKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CategoryPropertyKeyRepository extends JpaRepository<CategoryPropertyKey,Long> {
    List<CategoryPropertyKey> findAllByCategoryId(Category category);


    @Modifying
    @Transactional
    @Query("DELETE FROM CategoryPropertyKey a WHERE a.builtIn = false")
    void deleteAllByBuiltInIsFalse();

    @Query("SELECT COUNT(a) FROM CategoryPropertyKey a WHERE a.builtIn = false")
    int countByBuiltInIsFalse();

}
