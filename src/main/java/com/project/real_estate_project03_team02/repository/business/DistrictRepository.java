package com.project.real_estate_project03_team02.repository.business;

import com.project.real_estate_project03_team02.entity.concretes.business.City;
import com.project.real_estate_project03_team02.entity.concretes.business.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The DistrictRepository interface is responsible for accessing and managing District entities
 * in the database. It extends the JpaRepository interface, which provides generic CRUD (Create, Read,
 * Update, Delete) operations for the District entity.
 *
 * DistrictRepository serves as a data access layer for District entities, allowing the application
 * to interact with the underlying database to perform various operations such as saving, updating,
 * deleting, and querying District entities.
 */
@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {


    District findByNameAndCityId(String name, City cityId);
}
