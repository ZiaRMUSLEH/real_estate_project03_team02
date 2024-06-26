package com.project.real_estate_project03_team02.repository.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * The CountryRepository interface provides methods to perform CRUD (Create, Read, Update, Delete) operations
 * on Country entities in the database.
 *
 * This repository extends the JpaRepository interface provided by Spring Data JPA, which provides
 * out-of-the-box methods for common database operations without requiring manual implementation.
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {


    @Modifying
    @Transactional
    @Query("DELETE FROM Country")
    void removeAll();

    Country findByName(String name);
}
