package com.project.real_estate_project03_team02.payload.mappers.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.entity.concretes.business.Category;
import com.project.real_estate_project03_team02.entity.concretes.business.CategoryPropertyKey;
import com.project.real_estate_project03_team02.payload.request.business.AdvertRequest;
import com.project.real_estate_project03_team02.payload.response.business.AdvertResponse;
import com.project.real_estate_project03_team02.service.business.CategoryPropertyValueService;
import com.project.real_estate_project03_team02.service.business.ImagesService;
import com.project.real_estate_project03_team02.service.business.TourRequestService;
import com.project.real_estate_project03_team02.service.helper.CategoryServiceHelper;
import com.project.real_estate_project03_team02.service.helper.SlugGenerator;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Data
@Component
@RequiredArgsConstructor
public class AdvertRequestToAdvertMapper {


    private final CategoryServiceHelper categoryServiceHelper;
    private final CategoryPropertyValueService categoryPropertyValueService;

    private final ImagesService imagesService;

    private final TourRequestService tourRequestService;

    private final SlugGenerator slugGenerator;



    public Advert mapAdvertRequestToAdvert(AdvertRequest advertRequest){
        return Advert.builder()
                .title(advertRequest.getTitle())
                .description(advertRequest.getDescription())
                .price(advertRequest.getPrice())
                .location(advertRequest.getLocation())
                .build();
    }




}
