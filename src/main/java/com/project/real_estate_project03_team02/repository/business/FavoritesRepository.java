package com.project.real_estate_project03_team02.repository.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.entity.concretes.business.Favorite;
import com.project.real_estate_project03_team02.entity.concretes.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoritesRepository extends JpaRepository<Favorite,Long> {
    List<Favorite> findByUserId(User authenticatedUser);


    Favorite findByUserIdAndAdvertId(User authenticatedUser, Advert advert);
}
