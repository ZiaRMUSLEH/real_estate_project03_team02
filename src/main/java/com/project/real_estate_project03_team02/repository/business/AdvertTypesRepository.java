package com.project.real_estate_project03_team02.repository.business;

import com.project.real_estate_project03_team02.entity.concretes.business.AdvertType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertTypesRepository extends JpaRepository<AdvertType,Long> {

    @Modifying
    @Query("DELETE FROM AdvertType a WHERE a.builtIn = false")
    void deleteAllByBuiltInIsFalse();

    @Query("SELECT COUNT(a) FROM AdvertType a WHERE a.builtIn = false")
    int countByBuiltInIsFalse();




}
