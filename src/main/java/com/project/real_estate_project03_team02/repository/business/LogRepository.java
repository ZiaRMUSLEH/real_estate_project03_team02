package com.project.real_estate_project03_team02.repository.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * The LogRepository interface is responsible for providing access to the database for Log entities.
 * It extends the JpaRepository interface, which provides basic CRUD operations for Log entities.
 * This interface is annotated with @Repository to indicate that it is a Spring Data repository.
 */
@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Log")
    void removeAll();
}
