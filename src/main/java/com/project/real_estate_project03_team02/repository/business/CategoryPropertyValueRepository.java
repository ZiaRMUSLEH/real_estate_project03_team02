package com.project.real_estate_project03_team02.repository.business;


import com.project.real_estate_project03_team02.entity.concretes.business.CategoryPropertyKey;
import com.project.real_estate_project03_team02.entity.concretes.business.CategoryPropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * The CategoryPropertyValueRepository interface provides methods for accessing and managing
 * CategoryPropertyValue entities in the database. It extends the JpaRepository interface
 * provided by Spring Data JPA, which includes CRUD operations for the entity.
 *
 * This repository is specifically designed for the CategoryPropertyValue entity, which represents
 * the values associated with different categories of properties in the real estate domain.
 * Each CategoryPropertyValue instance is linked to a CategoryPropertyKey, which defines the
 * category and property type.
 *
 * This interface defines additional methods beyond the basic CRUD operations, such as finding
 * a CategoryPropertyValue by its associated CategoryPropertyKey.
 *
 * @see com.project.real_estate_project03_team02.entity.concretes.business.CategoryPropertyValue
 * @see com.project.real_estate_project03_team02.entity.concretes.business.CategoryPropertyKey
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
public interface CategoryPropertyValueRepository extends JpaRepository<CategoryPropertyValue, Long> {

    /**
     * Retrieves a CategoryPropertyValue by its associated CategoryPropertyKey.
     *
     * @param categoryPropertyKey The CategoryPropertyKey object associated with the
     *                            CategoryPropertyValue to be retrieved.
     * @return The CategoryPropertyValue object if found, or null if not found.
     */
    CategoryPropertyValue findByCategoryPropertyKey(CategoryPropertyKey categoryPropertyKey);

}
