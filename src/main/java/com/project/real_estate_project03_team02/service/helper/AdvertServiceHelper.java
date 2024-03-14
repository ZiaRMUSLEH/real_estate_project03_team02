package com.project.real_estate_project03_team02.service.helper;

import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.exception.ResourceNotFoundException;
import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import com.project.real_estate_project03_team02.repository.business.AdvertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * The AdvertServiceHelper class provides helper methods for managing Advert entities in the real estate project.
 * It encapsulates functionality related to retrieving Advert entities from the database.
 */
/**
 * The AdvertServiceHelper class provides helper methods for managing Advert entities in the real estate project.
 * It encapsulates functionality related to retrieving Advert entities from the database.
 */
@Component
@RequiredArgsConstructor
public class AdvertServiceHelper {

  private final AdvertRepository advertRepository;

  /**
   * Retrieves an Advert entity by its unique identifier.
   *
   * @param advertId The unique identifier of the Advert entity to retrieve.
   * @return The Advert entity corresponding to the provided ID.
   * @throws ResourceNotFoundException If the Advert entity with the specified ID is not found in the database.
   */
  public Advert findById(Long advertId) throws ResourceNotFoundException {
    return advertRepository.findById(advertId).orElseThrow(() ->
            new ResourceNotFoundException(String.format(
                    ErrorMessages.NOT_FOUND_ADVERT_MESSAGE, advertId)));
  }

    public Advert findBySlug(String slug)  throws ResourceNotFoundException{

    return advertRepository.findBySlug(slug).orElseThrow(() ->
            new ResourceNotFoundException(String.format(
                    ErrorMessages.NOT_FOUND_ADVERT_MESSAGE, slug)));
    }

}
