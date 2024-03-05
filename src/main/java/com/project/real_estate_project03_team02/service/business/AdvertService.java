package com.project.real_estate_project03_team02.service.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.entity.concretes.user.User;
import com.project.real_estate_project03_team02.exception.ResourceNotFoundException;
import com.project.real_estate_project03_team02.payload.mappers.business.AdvertMapper;
import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import com.project.real_estate_project03_team02.payload.messages.SuccessMessages;
import com.project.real_estate_project03_team02.payload.request.business.AdvertRequest;
import com.project.real_estate_project03_team02.payload.response.business.AdvertResponse;
import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.repository.business.AdvertRepository;
<<<<<<< HEAD
import com.project.real_estate_project03_team02.service.helper.PageableHelper;
import com.project.real_estate_project03_team02.service.user.UserRoleService;
=======
import com.project.real_estate_project03_team02.service.helper.AdvertServiceHelper;
>>>>>>> main
import com.project.real_estate_project03_team02.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class AdvertService {

    private final AdvertRepository advertRepository;
    private final AdvertMapper advertMapper;
    private final TourRequestService tourRequestService;
    private final AdvertServiceHelper advertServiceHelper;
    private final UserRoleService userRoleService;
    private final UserService userService;
    private final PageableHelper pageableHelper;
    private final AdvertRepository advertRepository;
    private final AdvertMapper advertMapper;

    /**
     * Retrieves all tour adverts associated with the authenticated user.
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
        return advertRepository.findAllByOwnerUserId(authenticatedUser.getId(), pageable).map(advertMapper::mapAdvertToAdvertResponse);
    }


    public ResponseMessage<AdvertResponse>save(AdvertRequest advertRequest) {

        // we have to save in the database as Advert, We have to map AdvertRequest to Advert to save it in the database.

        //We are mapping the DTO to entity and we are saving the entity.
        //we are map DTO-> Entity
        Advert advert = advertMapper.mapAdvertRequestToAdvert(advertRequest);

        Advert savedAdvert = advertRepository.save(advert);

        AdvertResponse advertResponse = advertMapper.mapAdvertToAdvertResponse(savedAdvert);
        advertResponse.setTourRequests(tourRequestService.findAllByAdvertId(advert.getId()));
        // we are returning response DTO by mapping the saved version of advert
        return ResponseMessage.<AdvertResponse>builder()
                .message(SuccessMessages.ADVERT_CREATED)
<<
                //.object(advertMapper.mapAdvertToAdvertResponse(savedAdvert))
                //.httpStatus(HttpStatus.CREATED)
=
                //.object(advertResponse)
>>
                //.build();

    }
}
