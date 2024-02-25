package com.project.real_estate_project03_team02.payload.mappers.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.entity.concretes.business.Category;
import com.project.real_estate_project03_team02.entity.concretes.business.CategoryPropertyKey;
import com.project.real_estate_project03_team02.entity.concretes.business.Images;
import com.project.real_estate_project03_team02.payload.request.business.AdvertRequest;
import com.project.real_estate_project03_team02.payload.response.business.AdvertResponse;
import com.project.real_estate_project03_team02.service.business.TourRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AdvertMapper {


    //private final ImagesService imagesService;

    private final TourRequestService tourRequestService;

    /**
     *
     * @param advertRequest DTO from UI
     * @return entity for DB
     */

    // we request an advert request and return an advert
    public Advert mapAdvertRequestToAdvert(AdvertRequest advertRequest){

        return Advert.builder()
                .title(advertRequest.getTitle())
                .description(advertRequest.getDescription())
                .price(advertRequest.getPrice())
                .location(advertRequest.getLocation())
                .build();
    }


    public AdvertResponse mapAdvertToAdvertResponse(Advert advert){

        ArrayList<Map<String, Long>> properties;
        Category category = advert.getCategoryId();
        // find category property key from its service by category id.
       // CategoryPropertyKey categoryPropertyKey = ...

        // find category property value from its service by category id.
        // CategoryPropertyValue categoryPropertyValue = ...

        return AdvertResponse.builder()
                .id(advert.getId())
                .title(advert.getTitle())






              //  .properties(advert.getCategoryId())
                // find image list by Advert id
               // .images(advert.)
             .tourRequests(tourRequestService.findAllByAdvertId(advert.getId()))
                .build();

    }

}
