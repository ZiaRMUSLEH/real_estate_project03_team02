package com.project.real_estate_project03_team02.service;


import com.project.real_estate_project03_team02.exception.BadRequestException;
import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import com.project.real_estate_project03_team02.repository.business.LogRepository;
import com.project.real_estate_project03_team02.repository.business.*;
import com.project.real_estate_project03_team02.repository.user.UserRepository;
import com.project.real_estate_project03_team02.repository.user.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        // Delete adverts that are not built-in
        advertRepository.deleteAllByBuiltInIsFalse();
        if(advertRepository.countByBuiltInIsFalse() != 0) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_ADVERTS);
        }

        // Delete advert types that are not built-in
        advertTypesRepository.deleteAllByBuiltInIsFalse();
        if(advertTypesRepository.countByBuiltInIsFalse() != 0) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_ADVERT_TYPES);
        }

        // Delete category property keys that are not built-in
        categoryPropertyKeyRepository.deleteAllByBuiltInIsFalse();
        if(categoryPropertyKeyRepository.countByBuiltInIsFalse() != 0) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_ADVERT_TYPES);
        }

        // Delete categories that are not built-in
        categoryRepository.deleteAllByBuiltInIsFalse();
        if(categoryRepository.countByBuiltInIsFalse() != 0) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_ADVERT_TYPES);
        }

        // Delete users that are not built-in
        userRepository.deleteAllByBuiltInIsFalse();
        if(userRepository.countByBuiltInIsFalse() != 0) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_ADVERT_TYPES);
        }

        // Attempt to delete category property values, cities, contact messages, countries, districts,
        // favorites, images, logs, tour requests, and user roles with corresponding error handling
        try {
            categoryPropertyValueRepository.deleteAll();
        } catch (BadRequestException e) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_CATEGORY_PROPERTY_VALUES);
        }
        try {
            cityRepository.deleteAll();
        } catch (BadRequestException e) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_CITIES);
        }
        try {
            contactMessageRepository.deleteAll();
        } catch (BadRequestException e) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_CONTACT_MESSAGES);
        }
        try {
            countryRepository.deleteAll();
        } catch (BadRequestException e) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_COUNTRIES);
        }
        try {
            districtRepository.deleteAll();
        } catch (BadRequestException e) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_DISTRICTS);
        }
        try {
            favoritesRepository.deleteAll();
        } catch (BadRequestException e) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_FAVORITES);
        }
        try {
            imagesRepository.deleteAll();
        } catch (BadRequestException e) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_IMAGES);
        }
        try {
            logRepository.deleteAll();
        } catch (BadRequestException e) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_LOGS);
        }
        try {
            tourRequestRepository.deleteAll();
        } catch (BadRequestException e) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_TOUR_REQUESTS);
        }
        try {
            userRoleRepository.deleteAll();
        } catch (BadRequestException e) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_ROLES);
        }
    }
}
