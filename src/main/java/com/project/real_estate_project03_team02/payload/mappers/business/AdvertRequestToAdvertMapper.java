package com.project.real_estate_project03_team02.payload.mappers.business;

import com.project.real_estate_project03_team02.entity.concretes.business.*;
import com.project.real_estate_project03_team02.entity.concretes.user.User;
import com.project.real_estate_project03_team02.entity.enums.AdvertStatus;
import com.project.real_estate_project03_team02.payload.request.business.AdvertRequest;
import com.project.real_estate_project03_team02.repository.business.AdvertRepository;
import com.project.real_estate_project03_team02.service.business.*;
import com.project.real_estate_project03_team02.service.helper.CategoryServiceHelper;
import com.project.real_estate_project03_team02.service.helper.SlugGenerator;
import com.project.real_estate_project03_team02.service.user.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

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

    private final AdvertTypesService advertTypesService;

    private final CountryService countryService;

    private  final CityService cityService;

    private final DistrictService districtService;

    private final UserService userService;

    private final CategoryService categoryService;

    private final ImagesMapper imagesMapper;

    private final AdvertRepository advertRepository;

    public Advert mapAdvertRequestToAdvert(AdvertRequest advertRequest ) {
        return Advert.builder()
                .title(advertRequest.getTitle())
                .description(advertRequest.getDescription())
                .price(advertRequest.getPrice())
                .location(advertRequest.getLocation())
                .build();
    }

    public Advert mapAdvertRequestToAdvert(AdvertRequest advertRequest, HttpServletRequest httpServletRequest) {
        Advert advert = mapAdvertRequestToAdvert(advertRequest);

        // Generate a slug for the advertisement title
        advert.setSlug(slugGenerator.generateSlug(advertRequest.getTitle()));

        // Set initial status and other properties
        advert.setStatus(AdvertStatus.PENDING);
        advert.setBuiltIn(false);
        advert.setActive(true);
        advert.setViewCount(0);
        advert.setAdvertTypeId(advertTypesService.findById(advertRequest.getAdvertTypeId()));

        // Save country information and set it to the advert
        Country country = countryService.save(advertRequest.getCountryId());
        advert.setCountryId(country);

        // Create or retrieve the city and set it to the advert
        City city = cityService.save(advertRequest.getCityId(), country);
        advert.setCityId(city);

        // Create or retrieve the district and set it to the advert
        District district = districtService.save(advertRequest.getDistrictId(), city);
        advert.setDistrictId(district);

        // Get authenticated user's email from the request and retrieve user details
        String authenticatedUserEmail = (String) httpServletRequest.getAttribute("username");
        User authenticatedUser = userService.findByEmail(authenticatedUserEmail);
        advert.setUserId(authenticatedUser);

        // Set category ID and creation timestamp
        advert.setCategoryId(categoryService.findById(advertRequest.getCategoryId()));
        advert.setCreatedAt(LocalDateTime.now());



        // Now, you can save category property values associated with the advert
        categoryPropertyValueService.saveCategoryPropertyValues(advertRequest.getProperties(), advert);

        // Save images associated with the advert
        Images images = imagesMapper.mapImagesRequestToImages(advertRequest.getImages());
        images.setAdvertId(advert);
        imagesService.save(images);

        // Save the advert first before saving associated properties and images
        return advertRepository.save(advert);
    }


}
