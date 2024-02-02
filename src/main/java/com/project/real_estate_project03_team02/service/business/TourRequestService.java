package com.project.real_estate_project03_team02.service.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.entity.concretes.business.TourRequest;
import com.project.real_estate_project03_team02.entity.concretes.user.User;
import com.project.real_estate_project03_team02.entity.enums.TourRequestStatus;
import com.project.real_estate_project03_team02.exception.ResourceNotFoundException;
import com.project.real_estate_project03_team02.payload.mappers.business.TourRequestMapper;
import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import com.project.real_estate_project03_team02.payload.messages.SuccessMessages;
import com.project.real_estate_project03_team02.payload.request.business.TourRequestRequest;
import com.project.real_estate_project03_team02.payload.response.business.TourRequestResponse;
import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.repository.business.TourRequestRepository;
import com.project.real_estate_project03_team02.service.helper.PageableHelper;
import com.project.real_estate_project03_team02.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TourRequestService {

    private final TourRequestRepository tourRequestRepository;
    private final PageableHelper pageableHelper;
    private final TourRequestMapper tourRequestMapper;

    private final UserService userService;
    private final AdvertService advertService;


    public Page<TourRequestResponse> getAllTourRequestOfAuthenticatedUser(HttpServletRequest httpServletRequest, int page, int size, String sort, String type) {
        String authenticatedUserEmail = (String) httpServletRequest.getAttribute("username");
        User authenticatedUser = userService.findByEmail(authenticatedUserEmail);
        Pageable pageable = pageableHelper.getPageableWithProperties(page, size, sort, type);
        return tourRequestRepository.findAllByOwnerUserId(authenticatedUser.getId(), pageable).map(tourRequestMapper::mapTourRequestToTourRequestResponse);
    }


    public ResponseMessage<TourRequestResponse> saveTourRequest(HttpServletRequest httpServletRequest,TourRequestRequest tourRequestRequest) {
        TourRequest tourRequest = tourRequestMapper.mapTourRequestRequestToTourRequest(tourRequestRequest);
        tourRequest.setStatus(TourRequestStatus.PENDING);
        Advert advert = advertService.findById(tourRequestRequest.getAdvertId());
        tourRequest.setAdvertId(advert);
        User ownerUser = advert.getUserId();
        tourRequest.setOwnerUserId(ownerUser);
        String authenticatedUserEmail = (String) httpServletRequest.getAttribute("username");
        User authenticatedUser = userService.findByEmail(authenticatedUserEmail);
        tourRequest.setGuestUserId(authenticatedUser);
        tourRequest.setCreatedAt(LocalDateTime.now());
        TourRequest savedTourRequest = tourRequestRepository.save(tourRequest);
        return ResponseMessage.<TourRequestResponse>builder()
                .message(SuccessMessages.TOUR_REQUEST_CREATED)
                .object(tourRequestMapper.mapTourRequestToTourRequestResponse(savedTourRequest))
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    public Page<TourRequestResponse> getAllTourRequests(int page, int size, String sort, String type) {
        Pageable pageable = pageableHelper.getPageableWithProperties(page, size, sort, type);
        Page<TourRequest> tourRequests = tourRequestRepository.findAll(pageable);
        if(tourRequests.isEmpty()){throw new ResourceNotFoundException(ErrorMessages.NO_TOUR_REQUEST_SAVED);}
        return tourRequests.map(tourRequestMapper::mapTourRequestToTourRequestResponse);
    }



    public ResponseEntity<TourRequestResponse> getTourRequestDetail(HttpServletRequest httpServletRequest, Long id) {
        String authenticatedUserEmail = (String) httpServletRequest.getAttribute("username");
        Boolean isUserExist = userService.existByEmail(authenticatedUserEmail);
        if(!isUserExist){throw new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_USER_MESSAGE_BY_EMAIL,authenticatedUserEmail));}

        TourRequest tourRequest = tourRequestRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_TOUR_REQUEST,id)));
        return ResponseEntity.ok(  tourRequestMapper.mapTourRequestToTourRequestResponse(tourRequest));
    }

    public ResponseEntity<TourRequestResponse> getTourRequestDetailById(Long id) {
        TourRequest tourRequest = tourRequestRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_TOUR_REQUEST,id)));
        return ResponseEntity.ok(tourRequestMapper.mapTourRequestToTourRequestResponse(tourRequest));

    }
}
