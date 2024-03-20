package com.project.real_estate_project03_team02.controller.business;


import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.payload.response.business.AdvertResponseIdAndTitle;

import com.project.real_estate_project03_team02.service.business.FavoritesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Controller class for managing user favorites in the real estate application.
 */
@RestController
@RequestMapping("/favorites")
@RequiredArgsConstructor
public class FavoritesController {

    private final FavoritesService favoritesService;

    /**
     * Retrieves all favorites of the authenticated user.
     * @param httpServletRequest HttpServletRequest object for retrieving user authentication details.
     * @return List of Advert objects representing the favorites of the authenticated user.
     */
    @GetMapping("/auth")
    public List<Advert> getAllFavoriteOfAuthenticatedUser(HttpServletRequest httpServletRequest) {
        return favoritesService.getAllFavoriteOfAuthenticatedUser(httpServletRequest);
    }

    /**
     * Retrieves all favorites of a specific user, accessible by admin or manager authorities.
     * @param id Long representing the user ID.
     * @return List of Advert objects representing the favorites of the specified user.
     */
    @GetMapping("/admin/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public List<Advert> getAllFavoriteOfUser(@PathVariable Long id) {
        return favoritesService.getAllFavoriteOfUser(id);
    }

    /**
     * Adds or removes an advert to/from the favorites of the authenticated user.
     * @param httpServletRequest HttpServletRequest object for retrieving user authentication details.
     * @param id Long representing the advert ID.
     * @return AdvertResponseForFavorites object containing the updated favorites' information.
     */
    @PostMapping("/{id}/auth")
    public AdvertResponseIdAndTitle addOrRemoveAdvertOfUser(HttpServletRequest httpServletRequest, @PathVariable Long id) {
        return favoritesService.addOrRemoveAdvertOfUser(httpServletRequest,id);
    }

    /**
     * Removes all favorites of the authenticated user.
     * @param httpServletRequest HttpServletRequest object for retrieving user authentication details.
     */
    @DeleteMapping("/auth")
    public void removeAllFavoritesOfAuthenticatedUser(HttpServletRequest httpServletRequest) {
        favoritesService.removeAllFavoritesOfAuthenticatedUser(httpServletRequest);
    }

    /**
     * Removes all favorites of a specific user, accessible by admin or manager authorities.
     * @param httpServletRequest HttpServletRequest object for retrieving user authentication details.
     */
    @DeleteMapping("/admin")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public void removeAllFavoritesOfUser(HttpServletRequest httpServletRequest) {
        favoritesService.removeAllFavoritesOfAuthenticatedUser(httpServletRequest);
    }

    /**
     * Removes favorites of a specific user for a given advert, accessible by admin or manager authorities.
     * @param httpServletRequest HttpServletRequest object for retrieving user authentication details.
     * @param id Long representing the user ID.
     */
    @DeleteMapping("/{id}/admin")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public void removeFavoritesOfUser(HttpServletRequest httpServletRequest, @PathVariable Long id) {
        favoritesService.removeFavoritesOfUser(httpServletRequest, id);
    }

}
