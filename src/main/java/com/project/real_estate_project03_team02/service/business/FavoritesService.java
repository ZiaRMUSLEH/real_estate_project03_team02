package com.project.real_estate_project03_team02.service.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.entity.concretes.business.Favorite;
import com.project.real_estate_project03_team02.entity.concretes.user.User;
import com.project.real_estate_project03_team02.exception.BadRequestException;
import com.project.real_estate_project03_team02.payload.mappers.business.AdvertMapperIdAndTitle;
import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import com.project.real_estate_project03_team02.payload.response.business.AdvertResponseIdAndTitle;
import com.project.real_estate_project03_team02.repository.business.FavoritesRepository;
import com.project.real_estate_project03_team02.service.helper.AdvertServiceHelper;
import com.project.real_estate_project03_team02.service.helper.PageableHelper;
import com.project.real_estate_project03_team02.service.user.UserService;
import lombok.RequiredArgsConstructor;
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

    private final AdvertServiceHelper advertServiceHelper;

    private final AdvertMapperIdAndTitle advertMapperIdAndTitle;


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



    public AdvertResponseIdAndTitle addOrRemoveAdvertOfUser(HttpServletRequest httpServletRequest, Long id) {
        String authenticatedUserEmail = (String) httpServletRequest.getAttribute("username");
        User authenticatedUser = userService.findByEmail(authenticatedUserEmail);
        Advert advert = advertServiceHelper.findById(id);

        Favorite existingFavorite = favoritesRepository.findByUserIdAndAdvertId(authenticatedUser, advert);

        if (existingFavorite != null) {
            favoritesRepository.deleteById(existingFavorite.getId());
            return new AdvertResponseIdAndTitle(id, advert.getTitle());
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
