package com.project.real_estate_project03_team02.payload.mappers.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.payload.request.business.AdvertRequest;
import com.project.real_estate_project03_team02.service.business.CategoryPropertyValueService;
import com.project.real_estate_project03_team02.service.business.ImagesService;
import com.project.real_estate_project03_team02.service.business.TourRequestService;
import com.project.real_estate_project03_team02.service.helper.CategoryServiceHelper;
import com.project.real_estate_project03_team02.service.helper.SlugGenerator;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * This class, AdvertRequestToAdvertMapper, is responsible for mapping AdvertRequest objects to Advert objects.
 * It provides functionality to convert data from the incoming AdvertRequest format to the internal Advert format,
 * facilitating the handling and processing of advertisement-related data within the real estate project.
 */
@Component
@Data
@RequiredArgsConstructor
public class AdvertRequestToAdvertMapper {

    // Dependencies required for mapping
    private final CategoryServiceHelper categoryServiceHelper;
    private final CategoryPropertyValueService categoryPropertyValueService;
    private final ImagesService imagesService;
    private final TourRequestService tourRequestService;
    private final SlugGenerator slugGenerator;

    /**
     * Maps an AdvertRequest object to an Advert object.
     *
     * @param advertRequest The AdvertRequest object containing data to be mapped.
     * @return An Advert object mapped from the provided AdvertRequest.
     */
    public Advert mapAdvertRequestToAdvert(AdvertRequest advertRequest){
        return Advert.builder()
                .title(advertRequest.getTitle())
                .description(advertRequest.getDescription())
                .price(advertRequest.getPrice())
                .location(advertRequest.getLocation())
                // Additional mapping can be added here if necessary
                .build();
    }
}
