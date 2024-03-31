/*
 * The TourRequestRepository interface provides methods for accessing and managing tour requests in the real estate project.
 * It extends the JpaRepository interface, allowing CRUD (Create, Read, Update, Delete) operations on TourRequest entities.
 */

package com.project.real_estate_project03_team02.repository.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.entity.concretes.business.TourRequest;
import com.project.real_estate_project03_team02.entity.concretes.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface TourRequestRepository extends JpaRepository<TourRequest, Long> {

    /*
     * Retrieves a page of tour requests associated with the authenticated user who owns the advertised property.
     *
     * @param authenticatedUser The user object representing the authenticated user.
     * @param pageable          Pagination information.
     * @return                  A page of TourRequest objects.
     */
    Page<TourRequest> findAllByOwnerUserId(User authenticatedUser, Pageable pageable);

    /*
     * Retrieves an optional tour request associated with the specified owner user.
     *
     * @param user  The user object representing the owner of the tour request.
     * @return      An optional TourRequest object.
     */
    Optional<TourRequest> findByOwnerUserId(User user);

    /*
     * Retrieves an optional tour request associated with the specified guest user.
     *
     * @param user  The user object representing the guest of the tour request.
     * @return      An optional TourRequest object.
     */
    Optional<TourRequest> findByGuestUserId(User user);

    /*
     * Retrieves a list of tour requests associated with the specified advert.
     *
     * @param advert    The advert object representing the advertised property.
     * @return          An List of TourRequest objects.
     */
    Set<TourRequest> findAllByAdvertId(Advert advert);


    /**
     * @param date1
     * @param date2
     * @param status
     * @return An List of TourRequest objects.
     */
    @Query("SELECT t FROM TourRequest t " +
            "WHERE t.tourDate BETWEEN :date1 AND :date2 "+
            "AND t.status = :status")
    List<TourRequest> findTourRequestByBetweenDate1AndDate2ByStatus(String date1, String date2, TourRequest status);
}
