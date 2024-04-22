package com.project.real_estate_project03_team02.service.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.entity.concretes.business.Favorite;
import com.project.real_estate_project03_team02.entity.concretes.user.User;
import com.project.real_estate_project03_team02.exception.BadRequestException;
import com.project.real_estate_project03_team02.exception.ResourceNotFoundException;
import com.project.real_estate_project03_team02.payload.mappers.business.AdvertMapperIdAndTitle;
import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import com.project.real_estate_project03_team02.payload.response.business.AdvertResponseIdAndTitle;
import com.project.real_estate_project03_team02.repository.business.FavoritesRepository;
import com.project.real_estate_project03_team02.service.helper.AdvertServiceHelper;
import com.project.real_estate_project03_team02.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;



/**
 * FavoritesService is a service class responsible for managing favorite adverts for users.
 * It provides methods for retrieving, adding, removing, and managing favorite adverts for both authenticated and specific users.
 * The service relies on the FavoritesRepository for database operations, UserService for user-related operations,
 * PageableHelper for pagination support, AdvertServiceHelper for advert-related operations,
 * and AdvertMapperForFavorites for mapping advert entities to response DTOs.
 */
@Service
@RequiredArgsConstructor
public class FavoritesService {

    private final FavoritesRepository favoritesRepository;
    private final UserService userService;
    private final AdvertServiceHelper advertServiceHelper;

    private final AdvertMapperIdAndTitle advertMapperIdAndTitle;







    /**
     * Retrieves all favorite adverts of the authenticated user.
     *
     * @param httpServletRequest The HTTP request object containing user authentication details.
     * @return A list of favorite adverts belonging to the authenticated user.
     */

    public List<Advert> getAllFavoriteOfAuthenticatedUser(HttpServletRequest httpServletRequest) {

        User authenticatedUser = userService.getAuthenticatedUser(httpServletRequest);

        List<Favorite> favoriteList = favoritesRepository.findByUserId(authenticatedUser);
        if(favoriteList.isEmpty()){
            throw new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_FAVORITE_FOR_USER_ID,authenticatedUser.getId()));
        }
        return favoriteList.stream().map(Favorite::getAdvertId).collect(Collectors.toList());

    }

    /**
     * Retrieves all favorite adverts of a specific user.
     *
     * @param id The ID of the user.
     * @return A list of favorite adverts belonging to the specified user.
     */


    public List<Advert> getAllFavoriteOfUser(Long id) {
        User user = userService.findById(id);
        List<Favorite> favoriteList = favoritesRepository.findByUserId(user);
        if(favoriteList.isEmpty()){
            throw new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_FAVORITE_FOR_USER_ID,id));
        }
        return favoriteList.stream().map(Favorite::getAdvertId).collect(Collectors.toList());
    }

    /**
     * Adds or removes an advert from the authenticated user's favorites.
     *
     * @param httpServletRequest The HTTP request object containing user authentication details.
     * @param id                 The ID of the advert to add or remove.
     * @return An AdvertResponseForFavorites object representing the added or removed advert.
     */

    @Transactional
    public AdvertResponseIdAndTitle addOrRemoveAdvertOfUser(HttpServletRequest httpServletRequest, Long id) {
        User authenticatedUser = userService.getAuthenticatedUser(httpServletRequest);
        Advert advert = advertServiceHelper.findById(id);

        Favorite existingFavorite = favoritesRepository.findByUserIdAndAdvertId(authenticatedUser, advert);
        if (existingFavorite != null) {
          favoritesRepository.deleteById(existingFavorite.getId());
           return advertMapperIdAndTitle.mapAdvertToAdvertResponseIdAndTitle(advert);
        } else {
            Favorite addedFavorite = Favorite.builder()
                    .userId(authenticatedUser)
                    .advertId(advert)
                    .createAt(LocalDateTime.now())
                    .build();
            Favorite savedFavorite = favoritesRepository.save(addedFavorite);
            return advertMapperIdAndTitle.mapAdvertToAdvertResponseIdAndTitle(savedFavorite.getAdvertId());
        }
    }

    /**
     * Removes all favorite adverts of the authenticated user.
     *
     * @param httpServletRequest The HTTP request object containing user authentication details.
     */
    @Transactional
    public void removeAllFavoritesOfAuthenticatedUser(HttpServletRequest httpServletRequest) {
        User authenticatedUser = userService.getAuthenticatedUser(httpServletRequest);
        List<Favorite> favoriteList = favoritesRepository.findByUserId(authenticatedUser);
        favoriteList.stream().map(Favorite::getId).forEach(favoritesRepository::deleteById);
    }





    /**
     * Removes a specific favorite advert of the authenticated user.
     *
     * @param httpServletRequest The HTTP request object containing user authentication details.
     * @param id                 The ID of the favorite advert to remove.
     */
    @Transactional
    public void removeFavoritesOfUser(HttpServletRequest httpServletRequest, Long id) {
        User authenticatedUser = userService.getAuthenticatedUser(httpServletRequest);
        List<Favorite> favoriteList = favoritesRepository.findByUserId(authenticatedUser);
        if (favoriteList.stream().anyMatch(t -> t.getId().equals(id))) {
            favoritesRepository.deleteById(id);
        } else {
            throw new BadRequestException(String.format(ErrorMessages.NOT_FOUND_FAVORITE_MESSAGE, id));
        }


    }
}
