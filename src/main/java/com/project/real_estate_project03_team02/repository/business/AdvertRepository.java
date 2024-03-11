package com.project.real_estate_project03_team02.repository.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Advert;

import com.project.real_estate_project03_team02.entity.concretes.business.AdvertType;
import com.project.real_estate_project03_team02.entity.concretes.business.Category;
import com.project.real_estate_project03_team02.entity.concretes.user.User;

import com.project.real_estate_project03_team02.entity.enums.AdvertStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AdvertRepository extends JpaRepository<Advert,Long> {



      @Query("SELECT a FROM Advert a " +
        "WHERE a.createAt BETWEEN :firstDate AND :secondDate " +
        "AND a.categoryId = :category " +
        "AND  a.advertTypeId = :type " +
        "AND  a.status = :status")
    List<Advert> findAdvertBetweenFirstDateAndSecondDateByCategoryByTypeByStatus(
            @Param("firstDate") LocalDate firstDate,
            @Param("secondDate") LocalDate secondDate,
            @Param("category") Category category,
            @Param("type") AdvertType type,
            @Param("status") Advert status
    );

    @Query("SELECT a FROM Advert a ORDER BY a.viewCount-1 DESC")
    List<Advert> findMostPopularProperties(@Param("amount") int amount);
    //TODO -------------------------------------------------query yi duzenle



    Optional<Advert> findByUserId(User user);














}
