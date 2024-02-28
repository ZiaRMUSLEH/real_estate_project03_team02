package com.project.real_estate_project03_team02.controller.business;


import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.entity.concretes.business.Favorite;
import com.project.real_estate_project03_team02.payload.response.business.AdvertResponseForFavorites;
import com.project.real_estate_project03_team02.payload.response.business.TourRequestResponse;
import com.project.real_estate_project03_team02.service.business.FavoritesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/favorites")
@RequiredArgsConstructor
public class FavoritesController {

    private final FavoritesService favoritesService;



    @GetMapping("/auth")
    public List<Advert> getAllFavoriteOfAuthenticatedUser(HttpServletRequest httpServletRequest) {
        return favoritesService.getAllFavoriteOfAuthenticatedUser(httpServletRequest);
    }


    @GetMapping("/admin/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public List<Advert> getAllFavoriteOfUser(@PathVariable Long id) {
        return favoritesService.getAllFavoriteOfUser(id);
    }


    @PostMapping("/{id}/auth")
    public AdvertResponseForFavorites addOrRemoveAdvertOfUser(HttpServletRequest httpServletRequest,@PathVariable Long id) {
        return favoritesService.addOrRemoveAdvertOfUser(httpServletRequest,id);
    }


    @DeleteMapping("/auth")
    public void removeAllFavoritesOfAuthenticatedUser(HttpServletRequest httpServletRequest) {
        favoritesService.removeAllFavoritesOfAuthenticatedUser(httpServletRequest);
    }

    @DeleteMapping("/admin")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public void removeAllFavoritesOfUser(HttpServletRequest httpServletRequest) {
        favoritesService.removeAllFavoritesOfUser(httpServletRequest);
    }

    @DeleteMapping("/{id}/admin")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public void removeFavoritesOfUser(HttpServletRequest httpServletRequest, @PathVariable Long id) {
        favoritesService.removeFavoritesOfUser(httpServletRequest, id);
    }


}
