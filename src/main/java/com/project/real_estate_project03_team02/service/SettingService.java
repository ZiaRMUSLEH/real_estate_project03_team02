package com.project.real_estate_project03_team02.service;


import com.project.real_estate_project03_team02.exception.BadRequestException;
import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import com.project.real_estate_project03_team02.repository.business.LogRepository;
import com.project.real_estate_project03_team02.repository.business.*;
import com.project.real_estate_project03_team02.repository.user.UserRepository;
import com.project.real_estate_project03_team02.repository.user.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SettingService {

    private final AdvertRepository advertRepository;
    private final AdvertTypesRepository advertTypesRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryPropertyKeyRepository categoryPropertyKeyRepository;
    private final CategoryPropertyValueRepository categoryPropertyValueRepository;
    private final CityRepository cityRepository;
    private final ContactMessageRepository contactMessageRepository;
    private final CountryRepository countryRepository;
    private final DistrictRepository districtRepository;
    private final FavoritesRepository favoritesRepository;
    private final ImagesRepository imagesRepository;
    private final LogRepository logRepository;
    private final TourRequestRepository tourRequestRepository;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;




    @Transactional
    public void resetDatabase() {


        // Attempt to delete category property values, cities, contact messages, countries, districts,
        // favorites, images, logs, tour requests, and user roles with corresponding error handling
        try {
            categoryPropertyValueRepository.removeAll();
        } catch (BadRequestException e) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_CATEGORY_PROPERTY_VALUES);
        }

        try {
            contactMessageRepository.removeAll();
        } catch (BadRequestException e) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_CONTACT_MESSAGES);
        }
        try {
            countryRepository.removeAll();
        } catch (BadRequestException e) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_COUNTRIES);
        }
        try {
            districtRepository.removeAll();
        } catch (BadRequestException e) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_DISTRICTS);
        }
        try {
            cityRepository.removeAll();
        } catch (BadRequestException e) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_CITIES);
        }
        try {
            favoritesRepository.removeAll();
        } catch (BadRequestException e) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_FAVORITES);
        }
        try {
            imagesRepository.removeAll();
        } catch (BadRequestException e) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_IMAGES);
        }
        try {
            logRepository.removeAll();
        } catch (BadRequestException e) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_LOGS);
        }
        try {
            tourRequestRepository.removeAll();
        } catch (BadRequestException e) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_TOUR_REQUESTS);
        }
        try {
            userRoleRepository.removeAll();
        } catch (BadRequestException e) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_ROLES);
        }

        // Delete advert types that are not built-in
        try{advertTypesRepository.deleteAllByBuiltInIsFalse();
        }catch (BadRequestException e)  {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_ADVERT_TYPES);
        }

        // Delete adverts that are not built-in
        try{advertRepository.deleteAllByBuiltInIsFalse();
        }catch (BadRequestException e)  {
        throw new BadRequestException(ErrorMessages.NOT_DELETED_ADVERTS);
        }

        // Delete category property keys that are not built-in
        try{categoryPropertyKeyRepository.deleteAllByBuiltInIsFalse();
        }catch (BadRequestException e)  {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_CATEGORY_PROPERTY_KEYS);
        }

        // Delete categories that are not built-in
        try{categoryRepository.deleteAllByBuiltInIsFalse();
        }catch (BadRequestException e)  {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_CATEGORIES);
        }

        // Delete users that are not built-in
   try{     userRepository.deleteAllByBuiltInIsFalse();
      }catch (BadRequestException e)  {
        throw new BadRequestException(ErrorMessages.NOT_DELETED_ADVERT_TYPES);
        }


    }



}
