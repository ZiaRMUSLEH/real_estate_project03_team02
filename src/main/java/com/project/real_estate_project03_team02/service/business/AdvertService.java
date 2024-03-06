package com.project.real_estate_project03_team02.service.business;

import com.project.real_estate_project03_team02.entity.concretes.business.*;
import com.project.real_estate_project03_team02.entity.concretes.user.User;
import com.project.real_estate_project03_team02.entity.enums.AdvertStatus;
import com.project.real_estate_project03_team02.payload.mappers.business.AdvertRequestToAdvertMapper;
import com.project.real_estate_project03_team02.payload.mappers.business.AdvertToAdvertResponseMapper;
import com.project.real_estate_project03_team02.payload.messages.SuccessMessages;
import com.project.real_estate_project03_team02.payload.request.business.AdvertRequest;
import com.project.real_estate_project03_team02.payload.response.business.AdvertResponse;
import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.repository.business.AdvertRepository;
import com.project.real_estate_project03_team02.service.helper.PageableHelper;
import com.project.real_estate_project03_team02.service.helper.SlugGenerator;
import com.project.real_estate_project03_team02.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AdvertService {

    private final AdvertRepository advertRepository;
    private final AdvertRequestToAdvertMapper advertRequestToAdvertMapper;
    private final TourRequestService tourRequestService;
    private final UserService userService;
    private final SlugGenerator slugGenerator;

    private final CategoryPropertyValueService categoryPropertyValueService;
    private final ImagesService imagesService;
    private final AdvertToAdvertResponseMapper advertToAdvertResponseMapper;
    private final PageableHelper pageableHelper;

    /**
     * Saves a new advertisement based on the provided advert request.
     *
     * @param httpServletRequest The HTTP servlet request containing necessary information.
     * @param advertRequest      The advert request containing details of the advertisement to be saved.
     * @return A ResponseMessage containing the saved advertisement response along with HTTP status and message.
     */
    public ResponseMessage<AdvertResponse> save(HttpServletRequest httpServletRequest, AdvertRequest advertRequest) {
        // Map the advert request to an Advert entity
        Advert advert = advertRequestToAdvertMapper.mapAdvertRequestToAdvert(advertRequest);

        // Generate a slug for the advertisement title
        advert.setSlug(slugGenerator.generateSlug(advertRequest.getTitle()));

        // Set initial status and other properties
        advert.setStatus(AdvertStatus.PENDING);
        advert.setBuiltIn(false);
        advert.setIsActive(true);
        advert.setViewCount(0);
        advert.setAdvertTypeId(advertRequest.getAdvertTypeId());
        advert.setCountryId(advertRequest.getCountryId());
        advert.setCityId(advertRequest.getCityId());
        advert.setDistrictId(advertRequest.getDistrictId());

        // Get authenticated user's email from the request and retrieve user details
        String authenticatedUserEmail = (String) httpServletRequest.getAttribute("username");
        User authenticatedUser = userService.findByEmail(authenticatedUserEmail);
        advert.setUserId(authenticatedUser);

        // Set category ID and creation timestamp
        advert.setCategoryId(advertRequest.getCategoryId());
        advert.setCreateAt(LocalDateTime.now());

        // Save category property values associated with the advert
        categoryPropertyValueService.saveCategoryPropertyValue(advertRequest);

        // Save images associated with the advert
        Images images = advertRequest.getImages();
        images.setAdvertId(advert);
        imagesService.save(images);

        // Save the advert to the database
        Advert savedAdvert = advertRepository.save(advert);

        // Map the saved advert to response format
        AdvertResponse advertResponse = advertToAdvertResponseMapper.mapAdvertToAdvertResponse(savedAdvert);

        // Retrieve tour requests associated with the advert
        advertResponse.setTourRequests(tourRequestService.findAllByAdvertId(advert));

        // Construct and return a ResponseMessage containing the saved advert response
        return ResponseMessage.<AdvertResponse>builder()
                .message(SuccessMessages.ADVERT_CREATED)
                .httpStatus(HttpStatus.CREATED)
                .object(advertResponse)
                .build();
    }


    /**
     * Retrieves all adverts associated with the authenticated user.
     *
     * @param httpServletRequest the HTTP servlet request containing user authentication information
     * @param page               the page number
     * @param size               the size of each page
     * @param sort               the sorting criteria
     * @param type               the type of sorting (ascending or descending)
     * @return a Page object containing advert responses
     */
    public Page<AdvertResponse> getAllAdvertOfAuthenticatedUser(HttpServletRequest httpServletRequest, int page, int size, String sort, String type) {
        String authenticatedUserEmail = (String) httpServletRequest.getAttribute("username");
        User authenticatedUser = userService.findByEmail(authenticatedUserEmail);
        Pageable pageable = pageableHelper.getPageableWithProperties(page, size, sort, type);
        return advertRepository.findAllByUserId(authenticatedUser, pageable).map(advertToAdvertResponseMapper::mapAdvertToAdvertResponse);
    }


    public Page<AdvertResponse> getAllAdverts(String q, Category categoryId, AdvertType advertTypeId, double priceStart, double priceEnd, int status, int page, int size, String sort, String type) {
        Pageable pageable = pageableHelper.getPageableWithProperties(page,size,sort,type);
        return advertRepository
                .findAll(pageable)
                .map(advertToAdvertResponseMapper::mapAdvertToAdvertResponse);

    }
}
