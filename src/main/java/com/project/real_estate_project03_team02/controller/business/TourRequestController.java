package com.project.real_estate_project03_team02.controller.business;


import com.project.real_estate_project03_team02.payload.request.business.TourRequestRequest;
import com.project.real_estate_project03_team02.payload.response.business.TourRequestResponse;
import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.service.business.TourRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * The TourRequestController class is responsible for handling HTTP requests related to tour requests in the real estate application.
 * It provides endpoints for both authenticated users and administrators to manage tour requests.
 */
@RestController
@RequestMapping("/tour-requests")
@RequiredArgsConstructor
public class TourRequestController {

    private final TourRequestService tourRequestService;

    /**
     * Retrieves all tour requests associated with the authenticated user.
     *
     * @param httpServletRequest The HttpServletRequest object containing the request information.
     * @param page               The page number for pagination. Default is 0.
     * @param size               The size of each page. Default is 20.
     * @param sort               The field to sort by. Default is 'category_id'.
     * @param type               The sorting order, either 'asc' (ascending) or 'desc' (descending). Default is 'asc'.
     * @return A Page object containing TourRequestResponse instances representing tour requests of the authenticated user.
     */
    @GetMapping("/auth")
    public Page<TourRequestResponse> getAllTourRequestOfAuthenticatedUser(HttpServletRequest httpServletRequest,
                                                                          @RequestParam(value = "page", defaultValue = "0") int page,
                                                                          @RequestParam(value = "size", defaultValue = "20") int size,
                                                                          @RequestParam(value = "sort", defaultValue = "category_id") String sort,
                                                                          @RequestParam(value = "type", defaultValue = "asc") String type) {
        return tourRequestService.getAllTourRequestOfAuthenticatedUser(httpServletRequest, page, size, sort, type);
    }

    /**
     * Retrieves all tour requests.
     *
     * @param page The page number for pagination. Default is 0.
     * @param size The size of each page. Default is 20.
     * @param sort The field to sort by. Default is 'category_id'.
     * @param type The sorting order, either 'asc' (ascending) or 'desc' (descending). Default is 'asc'.
     * @return A Page object containing TourRequestResponse instances representing all tour requests.
     */
    @GetMapping("/admin")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public Page<TourRequestResponse> getAllTourRequests(@RequestParam(value = "page", defaultValue = "0") int page,
                                                        @RequestParam(value = "size", defaultValue = "20") int size,
                                                        @RequestParam(value = "sort", defaultValue = "category_id") String sort,
                                                        @RequestParam(value = "type", defaultValue = "asc") String type) {
        return tourRequestService.getAllTourRequests(page, size, sort, type);
    }

    /**
     * Retrieves details of a specific tour request for the authenticated user.
     *
     * @param id                The ID of the tour request to retrieve.
     * @param httpServletRequest The HttpServletRequest object containing the request information.
     * @return ResponseEntity containing the TourRequestResponse representing the details of the tour request.
     */
    @GetMapping("/{id}/auth")
    public ResponseEntity<TourRequestResponse> getTourRequestDetail(@PathVariable Long id, HttpServletRequest httpServletRequest) {
        return tourRequestService.getTourRequestDetail(httpServletRequest, id);
    }

    /**
     * Retrieves details of a specific tour request for administrators.
     *
     * @param id The ID of the tour request to retrieve.
     * @return ResponseEntity containing the TourRequestResponse representing the details of the tour request.
     */
    @GetMapping("/{id}/admin")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<TourRequestResponse> getTourRequestDetailById(@PathVariable Long id) {
        return tourRequestService.getTourRequestDetailById(id);
    }

    /**
     * Saves a new tour request.
     *
     * @param httpServletRequest The HttpServletRequest object containing the request information.
     * @param tourRequestRequest The TourRequestRequest object containing the details of the tour request to be saved.
     * @return ResponseMessage containing the TourRequestResponse representing the saved tour request.
     */
    @PostMapping()
    public ResponseMessage<TourRequestResponse> saveTourRequest(HttpServletRequest httpServletRequest, @Valid @RequestBody TourRequestRequest tourRequestRequest) {
        return tourRequestService.saveTourRequest(httpServletRequest, tourRequestRequest);
    }


    /**
     * Updates an existing tour request identified by the given ID.
     *
     * @param tourRequestRequest The updated tour request details.
     * @param id                 The ID of the tour request to update.
     * @return A response message containing the updated tour request details.
     */
    @PutMapping("/{id}")
    public ResponseMessage<TourRequestResponse> updateTourRequest(@Valid @RequestBody TourRequestRequest tourRequestRequest, @PathVariable Long id) {
        return tourRequestService.updateTourRequest(tourRequestRequest, id);
    }

    /**
     * Cancels a tour request identified by the given ID.
     *
     * @param httpServletRequest The HTTP servlet request.
     * @param id                 The ID of the tour request to cancel.
     * @return A response message indicating the cancellation status.
     */
    @GetMapping("/{id}/cancel")
    public ResponseMessage<TourRequestResponse> cancelTourRequest(HttpServletRequest httpServletRequest, @PathVariable Long id) {
        return tourRequestService.cancelTourRequest(httpServletRequest, id);
    }

    /**
     * Approves a tour request identified by the given ID.
     *
     * @param id The ID of the tour request to approve.
     * @return A response message indicating the approval status.
     */
    @GetMapping("/{id}/approve")
    public ResponseMessage<TourRequestResponse> approveTourRequest(@PathVariable Long id) {
        return tourRequestService.approveTourRequest(id);
    }

    /**
     * Declines a tour request identified by the given ID.
     *
     * @param id The ID of the tour request to decline.
     * @return A response message indicating the decline status.
     */
    @GetMapping("/{id}/decline")
    public ResponseMessage<TourRequestResponse> declineTourRequest(@PathVariable Long id) {
        return tourRequestService.declineTourRequest(id);
    }

    /**
     * Deletes a tour request identified by the given ID.
     *
     * @param id The ID of the tour request to delete.
     * @return A response message indicating the deletion status.
     */
    @DeleteMapping("/{id}")
    public ResponseMessage<TourRequestResponse> deleteTourRequestById(@PathVariable Long id) {
        return tourRequestService.deleteTourRequestById(id);
    }





}







