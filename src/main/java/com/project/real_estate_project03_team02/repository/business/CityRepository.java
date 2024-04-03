package com.project.real_estate_project03_team02.repository.business;

import com.project.real_estate_project03_team02.entity.concretes.business.City;
import com.project.real_estate_project03_team02.entity.concretes.business.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing City entities in the real estate project.
 * This interface extends the JpaRepository interface provided by Spring Data JPA,
 * allowing CRUD (Create, Read, Update, Delete) operations to be performed on City entities
 * without the need for boilerplate code.
 *
 * <p>CityRepository handles the persistence of City entities in the database,
 * providing methods for querying, saving, updating, and deleting City records.</p>
 *
 * <p>By extending JpaRepository<City, Long>, this repository inherits various
 * methods for working with City entities, including findAll(), findById(),
 * save(), deleteById(), and more.</p>
 *
 * <p>This repository is annotated with @Repository, indicating that it's a
 * Spring-managed component responsible for database access and persistence operations
 * related to City entities.</p>
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see com.project.real_estate_project03_team02.entity.concretes.business.City
 */
@Repository
public interface CityRepository extends JpaRepository<City, Long> {


    City findByName(String name);

    City findByNameAndCountryId(String name, Country countryId);
    // No additional methods needed at this time
}
