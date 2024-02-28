package com.project.real_estate_project03_team02.service.helper;

import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.exception.ResourceNotFoundException;
import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import com.project.real_estate_project03_team02.repository.business.AdvertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdvertServiceHelper {

  private final AdvertRepository advertRepository;


  public Advert findById(Long advertId) {
    return advertRepository.findById(advertId).orElseThrow(()->new ResourceNotFoundException(String.format(
        ErrorMessages.NOT_FOUND_ADVERT_MESSAGE,advertId)));
  }

}
