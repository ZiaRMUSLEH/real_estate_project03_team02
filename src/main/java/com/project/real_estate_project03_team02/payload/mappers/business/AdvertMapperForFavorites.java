package com.project.real_estate_project03_team02.payload.mappers.business;


import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.payload.response.business.AdvertResponseForFavorites;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AdvertMapperForFavorites {


    public AdvertResponseForFavorites mapAdvertToAdvertResponseForFavorites(Advert advert){
        return AdvertResponseForFavorites.builder()
                .id(advert.getId())
                .title(advert.getTitle())
                .build();
    }

}
