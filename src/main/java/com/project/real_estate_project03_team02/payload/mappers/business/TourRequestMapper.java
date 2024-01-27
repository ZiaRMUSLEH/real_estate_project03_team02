package com.project.real_estate_project03_team02.payload.mappers.business;

import com.project.real_estate_project03_team02.entity.concretes.business.TourRequest;
import com.project.real_estate_project03_team02.payload.request.business.TourRequestRequest;
import com.project.real_estate_project03_team02.payload.response.business.TourRequestResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class TourRequestMapper {

    public TourRequest mapTourRequestRequestToTourRequest (TourRequestRequest tourRequestRequest){
        return TourRequest.builder()
                .tourDate(tourRequestRequest.getTourDate())
                .tourTime(tourRequestRequest.getTourTime())
                .build();
    }

    public TourRequestResponse mapTourRequestToTourRequestResponse(TourRequest tourRequest){
        return TourRequestResponse.builder()
                .id(tourRequest.getId())
                .tourDate(tourRequest.getTourDate())
                .advertId(tourRequest.getAdvertId())
                .ownerUserId(tourRequest.getOwnerUserId())
                .guestUserId(tourRequest.getGuestUserId())
                .build();
    }


}
