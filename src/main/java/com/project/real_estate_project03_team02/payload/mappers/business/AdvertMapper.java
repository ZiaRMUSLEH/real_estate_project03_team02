package com.project.real_estate_project03_team02.payload.mappers.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.payload.request.business.AdvertRequest;
import com.project.real_estate_project03_team02.payload.response.business.AdvertResponse;
import org.springframework.stereotype.Component;

@Component
public class AdvertMapper {

    /**
     *
     * @param advertRequest DTO from UI
     * @return entity for DB
     */

    // we request an advert request and return an advert
    public Advert mapAdvertRequestToAdvert(AdvertRequest advertRequest){

        // we use builder design pattern. Easy to use, we don't need to use getter setter
        // we don't have any inheritance so we use builder.
        return Advert.builder()
                .title(advertRequest.getTitle())
                .description(advertRequest.getDescription())
                .price(advertRequest.getPrice())
                .advertTypeId(advertRequest.getAdvertTypeId())
                .countryId(advertRequest.getCountryId())
                .cityId(advertRequest.getCityId())
                .districtId(advertRequest.getDistrictId())
                .categoryId(advertRequest.getCategoryId())
                .isActive(advertRequest.getIsActive())
                //.properties(advertRequest.getProperties())
                .build();
    }


    public AdvertResponse mapAdvertToAdvertResponse(Advert advert){
        return AdvertResponse.builder()
                .id(advert.getId())
                .title(advert.getTitle())
                //.images(advert.getImages())
                //.tour_requests(advert.getTourRequests)
                //.properties(advert.getProperties)
                .build();

    }

}
