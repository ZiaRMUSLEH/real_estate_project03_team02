package com.project.real_estate_project03_team02.repository.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.entity.concretes.business.Favorite;
import com.project.real_estate_project03_team02.entity.concretes.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * The FavoritesRepository interface provides methods to interact with the database
 * for managing favorite advertisements for users in the real estate project application.
 * It extends the JpaRepository interface to inherit basic CRUD (Create, Read, Update, Delete) operations
 * provided by Spring Data JPA.
 */
public interface FavoritesRepository extends JpaRepository<Favorite, Long> {

    /**
     * Retrieves a list of favorite entries based on the authenticated user.
     *
     * @param authenticatedUser The user for whom to retrieve the favorite entries.
     * @return A list of Favorite objects associated with the specified user.
     */
    List<Favorite> findByUserId(User authenticatedUser);

    /**
     * Retrieves a favorite entry based on the authenticated user and the advertisement.
     *
     * @param authenticatedUser The user for whom to retrieve the favorite entry.
     * @param advert             The advertisement for which to retrieve the favorite entry.
     * @return A Favorite object associated with the specified user and advertisement.
     */
    Favorite findByUserIdAndAdvertId(User authenticatedUser, Advert advert);
}

