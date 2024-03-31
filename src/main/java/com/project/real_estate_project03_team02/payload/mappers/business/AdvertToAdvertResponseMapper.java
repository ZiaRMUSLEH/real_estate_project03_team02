package com.project.real_estate_project03_team02.payload.mappers.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.entity.concretes.business.Category;
import com.project.real_estate_project03_team02.entity.concretes.business.CategoryPropertyKey;
import com.project.real_estate_project03_team02.payload.response.business.AdvertResponse;
import com.project.real_estate_project03_team02.service.business.CategoryPropertyValueService;
import com.project.real_estate_project03_team02.service.business.ImagesService;
import com.project.real_estate_project03_team02.service.business.TourRequestService;
import com.project.real_estate_project03_team02.service.helper.CategoryServiceHelper;
import com.project.real_estate_project03_team02.service.helper.SlugGenerator;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The AdvertToAdvertResponseMapper class is responsible for mapping an Advert entity to its corresponding AdvertResponse DTO.
 * It retrieves necessary information from the Advert entity and related services to construct the AdvertResponse object.
 * This mapper class facilitates the transformation of complex entity objects into simplified DTOs for presentation or API responses.
 */
@Component
@RequiredArgsConstructor
@Data
public class AdvertToAdvertResponseMapper {

    private final CategoryServiceHelper categoryServiceHelper;
    private final CategoryPropertyValueService categoryPropertyValueService;
    private final ImagesService imagesService;
    private final TourRequestService tourRequestService;
    private final SlugGenerator slugGenerator;

    /**
     * Maps an Advert entity to its corresponding AdvertResponse DTO.
     * Retrieves information about the advert's category, properties, images, and tour requests.
     *
     * @param advert The Advert entity to be mapped to an AdvertResponse DTO.
     * @return AdvertResponse object containing mapped data from the Advert entity.
     */
    public AdvertResponse mapAdvertToAdvertResponse(Advert advert) {
        // Retrieve the category of the advert
        Category category = advert.getCategoryId();
        // Find category property keys associated with the category
        List<CategoryPropertyKey> categoryPropertyKeys = categoryServiceHelper.findCategoryPropertyKeyByCategoryId(category);

        // Map category property keys to their corresponding values
        List<Map<String, String>> properties = categoryPropertyKeys.stream().map(categoryPropertyKey -> {
            String name = categoryPropertyKey.getName();
            // Retrieve and parse the property value
            String value =categoryPropertyValueService.findByCategoryPropertyKey(categoryPropertyKey).getValue();
            return Map.of(name, value);
        }).collect(Collectors.toList());

        // Construct the AdvertResponse object with mapped data
        return AdvertResponse.builder()
                .id(advert.getId())
                .title(advert.getTitle())
                .properties((List<Map<String, String>>)  properties)
                .images(imagesService.getImageDataByAdvertId(advert))
                .tourRequests(tourRequestService.findAllByAdvertId(advert))
                .build();
    }
}
