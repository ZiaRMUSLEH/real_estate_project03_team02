package com.project.real_estate_project03_team02.repository.business;

import com.project.real_estate_project03_team02.entity.concretes.business.AdvertType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link com.project.real_estate_project03_team02.entity.concretes.business.AdvertType} entities.
 * This repository provides CRUD (Create, Read, Update, Delete) operations for AdvertType entities.
 */
@Repository
public interface AdvertTypeRepository extends JpaRepository<AdvertType, Long> {
    // No additional methods are declared here because JpaRepository provides all necessary CRUD methods.
}
