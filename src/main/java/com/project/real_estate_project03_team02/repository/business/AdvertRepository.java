package com.project.real_estate_project03_team02.repository.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Advert;

import com.project.real_estate_project03_team02.entity.concretes.user.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AdvertRepository extends JpaRepository<Advert,Long> {


// aisfalhfjahsadg

//      @Query("SELECT a FROM Advert a " +
//        "WHERE a.createAt BETWEEN :firstDate AND :secondDate " +
//        "AND (:category IS NULL OR a.categoryId = :category) " +
//        "AND (:type IS NULL OR a.advertTypeId = :type) " +
//        "AND (:status IS NULL OR a.status = :status)")
//    List<Advert> findAdvertBetweenFirstDateAndSecondDateByCategoryByTypeByStatus(
//            @Param("firstDate") LocalDate firstDate,
//            @Param("secondDate") LocalDate secondDate,
//            @Param("category") Category category,
//            @Param("type") AdvertType type,
//            @Param("status") AdvertStatus status
//    );

    Optional<Advert> findByUserId(User user);



}
