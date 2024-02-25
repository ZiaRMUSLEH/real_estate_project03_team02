package com.project.real_estate_project03_team02.service.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.entity.concretes.business.Favorite;
import com.project.real_estate_project03_team02.entity.concretes.user.User;
import com.project.real_estate_project03_team02.exception.BadRequestException;
import com.project.real_estate_project03_team02.payload.mappers.business.AdvertMapperForFavorites;
import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import com.project.real_estate_project03_team02.payload.response.business.AdvertResponseForFavorites;
import com.project.real_estate_project03_team02.repository.business.FavoritesRepository;
import com.project.real_estate_project03_team02.service.helper.PageableHelper;
import com.project.real_estate_project03_team02.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoritesService {

    private final FavoritesRepository favoritesRepository;

    private final UserService userService;

    private final PageableHelper pageableHelper;

    private final AdvertService advertService;

    private final AdvertMapperForFavorites advertMapperForFavorites;


    public List<Advert> getAllFavoriteOfAuthenticatedUser(HttpServletRequest httpServletRequest) {

        String authenticatedUserEmail = (String) httpServletRequest.getAttribute("username");
        User authenticatedUser = userService.findByEmail(authenticatedUserEmail);

        List<Favorite> favoriteList = favoritesRepository.findByUserId(authenticatedUser);
        return favoriteList.stream().map(Favorite::getAdvertId).collect(Collectors.toList());

    }

    public List<Advert> getAllFavoriteOfUser(Long id) {
        User user = userService.findById(id);
        List<Favorite> favoriteList = favoritesRepository.findByUserId(user);
        return favoriteList.stream().map(Favorite::getAdvertId).collect(Collectors.toList());
    }



    public AdvertResponseForFavorites addOrRemoveAdvertOfUser(HttpServletRequest httpServletRequest, Long id) {
        String authenticatedUserEmail = (String) httpServletRequest.getAttribute("username");
        User authenticatedUser = userService.findByEmail(authenticatedUserEmail);
        Advert advert = advertService.findById(id);

        Favorite existingFavorite = favoritesRepository.findByUserIdAndAdvertId(authenticatedUser, advert);

        if (existingFavorite != null) {
            favoritesRepository.deleteById(existingFavorite.getId());
            return new AdvertResponseForFavorites(id, advert.getTitle());
        } else {
            Favorite addedFavorite = Favorite.builder()
                    .userId(authenticatedUser)
                    .advertId(advert)
                    .createAt(LocalDateTime.now())
                    .build();
            Favorite savedFavorite = favoritesRepository.save(addedFavorite);
            return advertMapperForFavorites.mapAdvertToAdvertResponseForFavorites(savedFavorite.getAdvertId());
        }
    }

    public void removeAllFavoritesOfAuthenticatedUser(HttpServletRequest httpServletRequest) {
        String authenticatedUserEmail = (String) httpServletRequest.getAttribute("username");
        User authenticatedUser = userService.findByEmail(authenticatedUserEmail);
        List<Favorite> favoriteList = favoritesRepository.findByUserId(authenticatedUser);
        favoriteList.stream().map(Favorite::getId).forEach(favoritesRepository::deleteById);
    }

    public void removeAllFavoritesOfUser(HttpServletRequest httpServletRequest) {
        String authenticatedUserEmail = (String) httpServletRequest.getAttribute("username");
        User authenticatedUser = userService.findByEmail(authenticatedUserEmail);
        List<Favorite> favoriteList = favoritesRepository.findByUserId(authenticatedUser);
        favoriteList.stream().map(Favorite::getId).forEach(favoritesRepository::deleteById);
    }

    public void removeFavoritesOfUser(HttpServletRequest httpServletRequest, Long id) {
        String authenticatedUserEmail = (String) httpServletRequest.getAttribute("username");
        User authenticatedUser = userService.findByEmail(authenticatedUserEmail);
        List<Favorite> favoriteList = favoritesRepository.findByUserId(authenticatedUser);
        if (favoriteList.stream().anyMatch(t -> t.getId().equals(id))) {
            favoritesRepository.deleteById(id);
        } else {
            throw new BadRequestException(String.format(ErrorMessages.NOT_FOUND_FAVORITE_MESSAGE, id));
        }


    }
}
