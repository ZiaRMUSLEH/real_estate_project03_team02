package com.project.real_estate_project03_team02.service.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.entity.concretes.business.TourRequest;
import com.project.real_estate_project03_team02.entity.concretes.user.User;
import com.project.real_estate_project03_team02.entity.enums.TourRequestStatus;
import com.project.real_estate_project03_team02.exception.BadRequestException;
import com.project.real_estate_project03_team02.exception.ResourceNotFoundException;
import com.project.real_estate_project03_team02.payload.mappers.business.TourRequestMapper;
import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import com.project.real_estate_project03_team02.payload.messages.SuccessMessages;
import com.project.real_estate_project03_team02.payload.request.business.TourRequestRequest;
import com.project.real_estate_project03_team02.payload.response.business.TourRequestResponse;
import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.repository.business.TourRequestRepository;
import com.project.real_estate_project03_team02.service.helper.AdvertServiceHelper;
import com.project.real_estate_project03_team02.service.helper.PageableHelper;
import com.project.real_estate_project03_team02.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Service class responsible for managing tour requests within the real estate project.
 * This class provides methods for retrieving, creating, updating, and deleting tour requests,
 * as well as approving, declining, and canceling them.
 * It interacts with the TourRequestRepository, UserService, AdvertService, and other necessary components.
 */
@Service
@RequiredArgsConstructor
public class TourRequestService {

    private final TourRequestRepository tourRequestRepository;
    private final PageableHelper pageableHelper;
    private final TourRequestMapper tourRequestMapper;

    private final UserService userService;
    private final AdvertServiceHelper advertServiceHelper;

    private final String userName = "username";

    /**
     * Retrieves all tour requests associated with the authenticated user.
     *
     * @param httpServletRequest the HTTP servlet request containing user authentication information
     * @param page               the page number
     * @param size               the size of each page
     * @param sort               the sorting criteria
     * @param type               the type of sorting (ascending or descending)
     * @return a Page object containing tour request responses
     */
    public Page<TourRequestResponse> getAllTourRequestOfAuthenticatedUser( HttpServletRequest httpServletRequest, int page, int size, String sort, String type) {
        String authenticatedUserEmail = (String) httpServletRequest.getAttribute(userName);
        User authenticatedUser = userService.findByEmail(authenticatedUserEmail);
        Pageable pageable = pageableHelper.getPageableWithProperties(page, size, sort, type);
        return tourRequestRepository.findAllByOwnerUserId(authenticatedUser, pageable).map(tourRequestMapper::mapTourRequestToTourRequestResponse);
    }


    /**
     * Retrieves all tour requests paginated and sorted.
     *
     * @param page the page number
     * @param size the size of each page
     * @param sort the sorting criteria
     * @param type the type of sorting (ascending or descending)
     * @return a Page object containing tour request responses
     * @throws ResourceNotFoundException if no tour requests are found
     */
    public Page<TourRequestResponse> getAllTourRequests(int page, int size, String sort, String type) {
        Pageable pageable = pageableHelper.getPageableWithProperties(page, size, sort, type);
        Page<TourRequest> tourRequests = tourRequestRepository.findAll(pageable);
        if (tourRequests.isEmpty()) {
            throw new ResourceNotFoundException(ErrorMessages.NO_TOUR_REQUEST_SAVED);
        }
        return tourRequests.map(tourRequestMapper::mapTourRequestToTourRequestResponse);
    }


    /**
     * Retrieves the details of a specific tour request by ID.
     *
     * @param httpServletRequest the HTTP servlet request containing user authentication information
     * @param id                 the ID of the tour request
     * @return a ResponseEntity containing the tour request response
     */
    public ResponseEntity<TourRequestResponse> getTourRequestDetail(HttpServletRequest httpServletRequest, Long id) {
        String authenticatedUserEmail = (String) httpServletRequest.getAttribute(userName);
        boolean isUserExist = userService.existByEmail(authenticatedUserEmail);
        if (!isUserExist) {
            throw new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_USER_MESSAGE_BY_EMAIL, authenticatedUserEmail));
        }

        TourRequest tourRequest = tourRequestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_TOUR_REQUEST, id)));
        return ResponseEntity.ok(tourRequestMapper.mapTourRequestToTourRequestResponse(tourRequest));
    }

    /**
     * Retrieves the details of a specific tour request by ID.
     *
     * @param id the ID of the tour request
     * @return a ResponseEntity containing the tour request response
     * @throws ResourceNotFoundException if the tour request is not found
     */
    public ResponseEntity<TourRequestResponse> getTourRequestDetailById(Long id) {
        TourRequest tourRequest = tourRequestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_TOUR_REQUEST, id)));
        return ResponseEntity.ok(tourRequestMapper.mapTourRequestToTourRequestResponse(tourRequest));

    }


    /**
     * Checks the validity of the tour date in a tour request.
     *
     * This method validates whether the tour date specified in the tour request is in the future,
     * ensuring that only future dates are allowed for tour requests.
     *
     * @param tourRequestRequest The tour request object containing the tour date to be checked.
     * @throws BadRequestException If the tour date is in the past (before the current date), a BadRequestException is thrown
     *                             indicating that the tour request date is invalid.
     */

    private void checkTourRequestRequestDate(TourRequestRequest tourRequestRequest) {
        if (tourRequestRequest.getTourDate().isBefore(LocalDate.now())) {
            throw new BadRequestException(ErrorMessages.INVALID_TOUR_REQUEST_DATE);
        }
    }


    /**
     * Saves a new tour request.
     *
     * @param httpServletRequest the HTTP servlet request containing user authentication information
     * @param tourRequestRequest the request object containing tour request details
     * @return a ResponseMessage containing the created tour request response
     */
    public ResponseMessage<TourRequestResponse> saveTourRequest(HttpServletRequest httpServletRequest, TourRequestRequest tourRequestRequest) {
        checkTourRequestRequestDate(tourRequestRequest);
        TourRequest tourRequest = tourRequestMapper.mapTourRequestRequestToTourRequest(tourRequestRequest);
        tourRequest.setStatus(TourRequestStatus.PENDING);
        Advert advert = advertServiceHelper.findById(tourRequestRequest.getAdvertId());
        tourRequest.setAdvertId(advert);
        User ownerUser = advert.getUserId();
        tourRequest.setOwnerUserId(ownerUser);
        String authenticatedUserEmail = (String) httpServletRequest.getAttribute(userName);
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

    /**
     * Retrieves a Tour Request by its ID.
     *
     * @param id The ID of the Tour Request to retrieve.
     * @return The Tour Request if found.
     * @throws ResourceNotFoundException if the Tour Request with the given ID is not found.
     */
    private TourRequest isTourRequestExist(Long id) {
        return tourRequestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_TOUR_REQUEST, id)));
    }

    /**
     * Updates a Tour Request with the provided details.
     *
     * @param tourRequestRequest The request containing updated Tour Request information.
     * @param id                 The ID of the Tour Request to update.
     * @return A ResponseMessage containing the updated Tour Request details.
     * @throws BadRequestException if the Tour Request status is not updatable.
     */
    public ResponseMessage<TourRequestResponse> updateTourRequest(TourRequestRequest tourRequestRequest, Long id) {
        checkTourRequestRequestDate(tourRequestRequest);
        TourRequest tourRequest = isTourRequestExist(id);
        if (!(Objects.equals(tourRequest.getStatus(), 0)) || !(Objects.equals(tourRequest.getStatus(), 2))) {
            throw new BadRequestException(String.format(ErrorMessages.NOT_UPDATABLE_TOUR_REQUEST, id));
        }
        tourRequest = tourRequestMapper.mapTourRequestRequestToTourRequest(tourRequestRequest);
        tourRequest.setStatus(TourRequestStatus.PENDING);
        Advert advert = advertServiceHelper.findById(tourRequestRequest.getAdvertId());
        tourRequest.setAdvertId(advert);
        tourRequest.setUpdatedAt(LocalDateTime.now());
        TourRequest savedTourRequest = tourRequestRepository.save(tourRequest);
        return ResponseMessage.<TourRequestResponse>builder()
                .message(SuccessMessages.TOUR_REQUEST_UPDATED)
                .object(tourRequestMapper.mapTourRequestToTourRequestResponse(savedTourRequest))
                .httpStatus(HttpStatus.OK)
                .build();
    }


    /**
     * Cancels a Tour Request.
     *
     * @param httpServletRequest The HTTP servlet request.
     * @param id                 The ID of the Tour Request to cancel.
     * @return A ResponseMessage confirming the cancellation of the Tour Request.
     * @throws BadRequestException if the provided Tour Request ID is invalid.
     */
    public ResponseMessage<TourRequestResponse> cancelTourRequest(HttpServletRequest httpServletRequest, Long id) {
        TourRequest tourRequest = isTourRequestExist(id);
        String authenticatedUserEmail = (String) httpServletRequest.getAttribute(userName);
        User authenticatedUser = userService.findByEmail(authenticatedUserEmail);
        if (!(tourRequest.getGuestUserId() == authenticatedUser)) {
            throw new BadRequestException(String.format(ErrorMessages.INVALID_TOUR_REQUEST_ID, id));
        }
        tourRequest.setStatus(TourRequestStatus.CANCELED);
        TourRequest savedTourRequest = tourRequestRepository.save(tourRequest);
        return ResponseMessage.<TourRequestResponse>builder()
                .message(SuccessMessages.TOUR_REQUEST_CANCELED)
                .object(tourRequestMapper.mapTourRequestToTourRequestResponse(savedTourRequest))
                .httpStatus(HttpStatus.OK)
                .build();
    }


    /**
     * Approves a Tour Request.
     *
     * @param id The ID of the Tour Request to approve.
     * @return A ResponseMessage confirming the approval of the Tour Request.
     */
    public ResponseMessage<TourRequestResponse> approveTourRequest(Long id) {

        TourRequest tourRequest = isTourRequestExist(id);
        tourRequest.setStatus(TourRequestStatus.APPROVED);
        TourRequest savedTourRequest = tourRequestRepository.save(tourRequest);
        return ResponseMessage.<TourRequestResponse>builder()
                .message(SuccessMessages.TOUR_REQUEST_ACCEPTED)
                .object(tourRequestMapper.mapTourRequestToTourRequestResponse(savedTourRequest))
                .httpStatus(HttpStatus.OK)
                .build();
    }

    /**
     * Declines a Tour Request.
     *
     * @param id The ID of the Tour Request to decline.
     * @return A ResponseMessage confirming the decline of the Tour Request.
     */
    public ResponseMessage<TourRequestResponse> declineTourRequest(Long id) {

        TourRequest tourRequest = isTourRequestExist(id);
        tourRequest.setStatus(TourRequestStatus.DECLINED);
        TourRequest savedTourRequest = tourRequestRepository.save(tourRequest);
        return ResponseMessage.<TourRequestResponse>builder()
                .message(SuccessMessages.TOUR_REQUEST_DECLINED)
                .object(tourRequestMapper.mapTourRequestToTourRequestResponse(savedTourRequest))
                .httpStatus(HttpStatus.OK)
                .build();
    }

    /**
     * Deletes a Tour Request by its ID.
     *
     * @param id The ID of the Tour Request to delete.
     * @return A ResponseMessage confirming the deletion of the Tour Request.
     */
    public ResponseMessage<TourRequestResponse> deleteTourRequestById(Long id) {
        TourRequest deletedTourRequest = isTourRequestExist(id);
        tourRequestRepository.deleteById(id);
        return ResponseMessage.<TourRequestResponse>builder()
                .message(SuccessMessages.TOUR_REQUEST_DELETED)
                .object(tourRequestMapper.mapTourRequestToTourRequestResponse(deletedTourRequest))
                .httpStatus(HttpStatus.OK)
                .build();

    }

    /**
     * Retrieves the count of all tour requests.
     *
     * @return The total count of tour requests.
     */

    public long getCountTourRequest() {
      return  tourRequestRepository.count();

    }

    /**
     * Retrieves all tour requests associated with a specific advert.
     *
     * @param advert The advert to filter tour requests by.
     * @return A list of tour requests associated with the advert.
     */

    public Set<TourRequest> findAllByAdvertId(Advert advert) {
        return tourRequestRepository.findAllByAdvertId(advert);
    }
}
