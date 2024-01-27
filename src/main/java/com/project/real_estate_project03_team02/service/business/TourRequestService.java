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
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TourRequestService {

    private final TourRequestRepository tourRequestRepository;
    private final PageableHelper pageableHelper;
    private final TourRequestMapper tourRequestMapper;

    private final UserService userService;
    private final AdvertService advertService;


    public Page<TourRequestResponse> getAllTourRequestOfAuthenticatedUser(HttpServletRequest httpServletRequest, int page, int size, String sort, String type) {
        Long authenticatedUserId = (Long) httpServletRequest.getAttribute("id");
        Pageable pageable = pageableHelper.getPageableWithProperties(page, size, sort, type);
        return tourRequestRepository.findAllByOwnerUserId(authenticatedUserId, pageable).map(tourRequestMapper::mapTourRequestToTourRequestResponse);
    }


    public ResponseMessage<TourRequestResponse> saveTourRequest(HttpServletRequest httpServletRequest,TourRequestRequest tourRequestRequest) {
        TourRequest tourRequest = tourRequestMapper.mapTourRequestRequestToTourRequest(tourRequestRequest);
        tourRequest.setStatus(TourRequestStatus.PENDING);
        Advert advert = advertService.findById(tourRequestRequest.getAdvertId());
        tourRequest.setAdvertId(advert);
        User ownerUser = advert.getUserId();
        tourRequest.setOwnerUserId(ownerUser);
        Long authenticatedUserId = (Long) httpServletRequest.getAttribute("id");
        User authenticatedUser = userService.findById(authenticatedUserId);
        tourRequest.setGuestUserId(authenticatedUser);
        tourRequest.setCreatedAt(LocalDateTime.now());

        TourRequest savedTourRequest = tourRequestRepository.save(tourRequest);
        return ResponseMessage.<TourRequestResponse>builder()
                .message(SuccessMessages.TOUR_REQUEST_CREATED)
                .object(tourRequestMapper.mapTourRequestToTourRequestResponse(savedTourRequest))
                .httpStatus(HttpStatus.CREATED)
                .build();



    }
}
