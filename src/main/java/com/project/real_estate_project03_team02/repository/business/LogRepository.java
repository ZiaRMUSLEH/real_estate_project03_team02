package com.project.real_estate_project03_team02.repository.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The LogRepository interface is responsible for providing access to the database for Log entities.
 * It extends the JpaRepository interface, which provides basic CRUD operations for Log entities.
 * This interface is annotated with @Repository to indicate that it is a Spring Data repository.
 */
@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
    // This interface doesn't contain any additional methods because JpaRepository
    // already provides methods for CRUD operations, such as save(), findById(), findAll(), delete(), etc.
}
