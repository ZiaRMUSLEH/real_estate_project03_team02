package com.project.real_estate_project03_team02.service.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.exception.ResourceNotFoundException;
import com.project.real_estate_project03_team02.payload.mappers.business.AdvertMapper;
import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import com.project.real_estate_project03_team02.payload.messages.SuccessMessages;
import com.project.real_estate_project03_team02.payload.request.business.AdvertRequest;
import com.project.real_estate_project03_team02.payload.response.business.AdvertResponse;
import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.repository.business.AdvertRepository;
import com.project.real_estate_project03_team02.service.helper.AdvertServiceHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdvertService {

    private final AdvertRepository advertRepository;
    private final AdvertMapper advertMapper;
    private final TourRequestService tourRequestService;
    private final AdvertServiceHelper advertServiceHelper;






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
                .object(advertResponse)
                .build();

    }
}
