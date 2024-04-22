package com.project.real_estate_project03_team02.payload.mappers.business;

import com.project.real_estate_project03_team02.entity.concretes.business.TourRequest;
import com.project.real_estate_project03_team02.payload.request.business.TourRequestRequest;
import com.project.real_estate_project03_team02.payload.response.business.TourRequestResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * This class provides mapping functions for converting between different representations
 * of TourRequest objects: from request payloads to entity objects and vice versa.
 * It handles the mapping of TourRequestRequest objects to TourRequest entity objects,
 * and TourRequest entity objects to TourRequestResponse objects.
 *
 * The mapping functions provided by this class enable seamless conversion between
 * request payloads and entity objects, allowing for efficient data transfer and
 * processing within the real estate project application.
 *
 * This class is annotated with the {@code @Component} annotation to indicate that
 * it is a Spring component and can be automatically detected and registered
 * in the Spring application context for dependency injection.
 */
@Data
@Component
public class TourRequestMapper {

    /**
     * Maps a TourRequestRequest object to a TourRequest entity object.
     *
     * @param tourRequestRequest The TourRequestRequest object to be mapped.
     * @return A TourRequest entity object mapped from the provided TourRequestRequest.
     */
    public TourRequest mapTourRequestRequestToTourRequest(TourRequestRequest tourRequestRequest) {
        return TourRequest.builder()
                .tourDate(tourRequestRequest.getTourDate())
                .tourTime(tourRequestRequest.getTourTime())
                .build();
    }

    /**
     * Maps a TourRequest entity object to a TourRequestResponse object.
     *
     * @param tourRequest The TourRequest entity object to be mapped.
     * @return A TourRequestResponse object mapped from the provided TourRequest.
     */
    public TourRequestResponse mapTourRequestToTourRequestResponse(TourRequest tourRequest) {
        return TourRequestResponse.builder()
                .id(tourRequest.getId())
                .tourDate(tourRequest.getTourDate())
                .advertId(tourRequest.getAdvertId())
                .ownerUserId(tourRequest.getOwnerUserId())
                .guestUserId(tourRequest.getGuestUserId())
                .build();
    }



    /**
     * Maps a TourRequestRequest object to an updated TourRequest object.
     * This method is used to update an existing TourRequest with new information provided in a TourRequestRequest.
     *
     * @param tourRequestRequest The TourRequestRequest object containing the updated information.
     * @param tourRequestId      The ID of the TourRequest to be updated.
     * @return An updated TourRequest object with the provided information.
     */
    public TourRequest mapTourRequestRequestToUpdateTourRequest(TourRequestRequest tourRequestRequest, Long tourRequestId) {
        return TourRequest.builder()
                .id(tourRequestId)
                .tourDate(tourRequestRequest.getTourDate())
                .tourTime(tourRequestRequest.getTourTime())
                .build();
    }
}
