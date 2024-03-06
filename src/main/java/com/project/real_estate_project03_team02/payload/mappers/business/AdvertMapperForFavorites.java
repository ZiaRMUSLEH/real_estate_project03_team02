package com.project.real_estate_project03_team02.payload.mappers.business;


import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.payload.response.business.AdvertResponseForFavorites;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AdvertMapperForFavorites {


    /**
     * Maps an Advert object to an AdvertResponseForFavorites object.
     *
     * This method takes an Advert object as input and creates a new AdvertResponseForFavorites object
     * with selected attributes from the input Advert. It's particularly useful for generating responses
     * tailored for favorites or similar use cases where only specific information is needed.
     *
     * @param advert The Advert object to be mapped to an AdvertResponseForFavorites object.
     * @return An AdvertResponseForFavorites object containing the mapped attributes from the input Advert.
     */
    public AdvertResponseForFavorites mapAdvertToAdvertResponseForFavorites(Advert advert){
        return AdvertResponseForFavorites.builder()
                .id(advert.getId())     // Sets the ID of the AdvertResponseForFavorites object to the ID of the input Advert.
                .title(advert.getTitle())  // Sets the title of the AdvertResponseForFavorites object to the title of the input Advert.
                .build();   // Builds and returns the AdvertResponseForFavorites object.
    }


}
