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
import com.project.real_estate_project03_team02.service.user.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdvertService {

    private final AdvertRepository advertRepository;
    private final AdvertMapper advertMapper;

    private final UserRoleService userRoleService;


    public Advert findById(Long advertId) {
        return advertRepository.findById(advertId).orElseThrow(()->new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_ADVERT_MESSAGE,advertId)));
    }

    public ResponseMessage<AdvertResponse>saveAdvert(AdvertRequest advertRequest) {

        // we have to save in the database as Advert, We have to map AdvertRequest to Advert to save it in the database.

        //We are mapping the DTO to entity and we are saving the entity.
        //we are map DTO-> Entity
        Advert advert = advertMapper.mapAdvertRequestToAdvert(advertRequest);

        Advert savedAdvert = advertRepository.save(advert);

        // we are returning response DTO by mapping the saved version of advert
        return ResponseMessage.<AdvertResponse>builder()
                .message(SuccessMessages.ADVERT_CREATED)
                .object(advertMapper.mapAdvertToAdvertResponse(savedAdvert))
                .httpStatus(HttpStatus.CREATED)
                .build();

    }
}
