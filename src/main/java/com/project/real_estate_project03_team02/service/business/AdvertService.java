package com.project.real_estate_project03_team02.service.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.entity.concretes.business.CategoryPropertyKey;
import com.project.real_estate_project03_team02.entity.concretes.business.CategoryPropertyValue;
import com.project.real_estate_project03_team02.entity.concretes.business.Images;
import com.project.real_estate_project03_team02.entity.concretes.user.User;
import com.project.real_estate_project03_team02.entity.enums.AdvertStatus;
import com.project.real_estate_project03_team02.exception.ResourceNotFoundException;
import com.project.real_estate_project03_team02.payload.mappers.business.AdvertRequestToAdvertMapper;
import com.project.real_estate_project03_team02.payload.mappers.business.AdvertToAdvertResponseMapper;
import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import com.project.real_estate_project03_team02.payload.messages.SuccessMessages;
import com.project.real_estate_project03_team02.payload.request.business.AdvertRequest;
import com.project.real_estate_project03_team02.payload.response.business.AdvertResponse;
import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.repository.business.AdvertRepository;
import com.project.real_estate_project03_team02.service.helper.CategoryServiceHelper;
import com.project.real_estate_project03_team02.service.helper.SlugGenerator;
import com.project.real_estate_project03_team02.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AdvertService {

    private final AdvertRepository advertRepository;
    private final AdvertRequestToAdvertMapper advertRequestToAdvertMapper;
    private final TourRequestService tourRequestService;

    private final AdvertTypesService advertTypesService;

    private final UserService userService;

    private final CategoryService categoryService;

    private final SlugGenerator slugGenerator;

    private final CityService cityService;

    private final CountryService countryService;

    private  final  DistrictService districtService;


    private final CategoryPropertyValueService categoryPropertyValueService;

    private final ImagesService imagesService;

    private final AdvertToAdvertResponseMapper advertToAdvertResponseMapper;




    public ResponseMessage<AdvertResponse>save(HttpServletRequest httpServletRequest, AdvertRequest advertRequest) {

        Advert advert = advertRequestToAdvertMapper.mapAdvertRequestToAdvert(advertRequest);
        advert.setSlug(slugGenerator.generateSlug(advertRequest.getTitle()));
        advert.setStatus(AdvertStatus.PENDING);
        advert.setBuiltIn(false);
        advert.setIsActive(true);
        advert.setViewCount(0);
        advert.setAdvertTypeId(advertTypesService.findById(advertRequest.getAdvertTypeId()));
        advert.setCountryId(countryService.findById(advertRequest.getCountryId()));
        advert.setCityId(cityService.findById(advertRequest.getCityId()));
        advert.setDistrictId(districtService.findById(advertRequest.getDistrictId()));
        String authenticatedUserEmail = (String) httpServletRequest.getAttribute("username");
        User authenticatedUser = userService.findByEmail(authenticatedUserEmail);
        advert.setUserId(authenticatedUser);
        advert.setCategoryId(categoryService.findById(advertRequest.getCategoryId()));
        advert.setCreateAt(LocalDateTime.now());

        categoryPropertyValueService.saveCategoryPropertyValue(advertRequest);

        Images images = advertRequest.getImages();
        images.setAdvertId(advert);
        imagesService.save(images);

        Advert savedAdvert = advertRepository.save(advert);
        AdvertResponse advertResponse = advertToAdvertResponseMapper.mapAdvertToAdvertResponse(savedAdvert);
        advertResponse.setTourRequests(tourRequestService.findAllByAdvertId(advert));
        return ResponseMessage.<AdvertResponse>builder()
                .message(SuccessMessages.ADVERT_CREATED)
                .object(advertResponse)
                .build();

    }

}
