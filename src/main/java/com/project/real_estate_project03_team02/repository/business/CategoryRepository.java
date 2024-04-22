package com.project.real_estate_project03_team02.repository.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Page<Category> findByIsActive(boolean isActive, Pageable pageable);
    Page<Category> findByTitleContaining(String q, Pageable pageable);
    Page<Category> findByTitleContainingAndIsActive(String q,boolean isActive, Pageable pageable);

    @Modifying
    @Transactional
    @Query("DELETE FROM Category a WHERE a.builtIn = false")
    void deleteAllByBuiltInIsFalse();

    @Query("SELECT COUNT(a) FROM Category a WHERE a.builtIn = false")
    int countByBuiltInIsFalse();


}
