package com.project.real_estate_project03_team02.repository.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.entity.concretes.business.AdvertType;
import com.project.real_estate_project03_team02.entity.concretes.business.Category;
import com.project.real_estate_project03_team02.entity.concretes.user.User;

import com.project.real_estate_project03_team02.entity.enums.AdvertStatus;
import com.project.real_estate_project03_team02.payload.request.business.AdvertRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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



    /**
     * Retrieves an advertisement posted by a specific user.
     *
     * @param user The user whose advertisement is to be retrieved.
     * @return An Optional containing the advertisement posted by the specified user if found,
     *         otherwise an empty Optional.
     * @throws NullPointerException if the user parameter is null.
     */
    Optional<Advert> findByUserId(User user);

    /**
     * Retrieves an advertisement belonging to a specific category.
     *
     * @param category The category to which the advertisement belongs.
     * @return An Optional containing the advertisement belonging to the specified category if found,
     *         otherwise an empty Optional.
     * @throws NullPointerException if the category parameter is null.
     */
    Optional<Advert> findByCategoryId(Category category);


    Page<Advert> findAllByUserId(User id, Pageable pageable);

    Optional<Advert> findBySlug(String slug);

    Page<Advert> findByTitleContainingAndIsActiveAndOptionalParameters(String q, Boolean isActive, Category category, AdvertType advertType, Double aDouble, Double aDouble1, Integer integer, Pageable pageable);

    Page<Advert> findByTitleContainingAndIsActive(String q, Boolean isActive, Pageable pageable);

    Page<Advert> findByIsActiveAndCategoryIdAndAdvertTypeIdAndPriceBetweenAndStatus(Boolean isActive, Category category, AdvertType advertType, Double aDouble, Double aDouble1, Integer integer, Pageable pageable);

    Page<Advert> findByIsActive(Boolean isActive, Pageable pageable);
}


    
