package com.project.real_estate_project03_team02.service;


import com.project.real_estate_project03_team02.exception.BadRequestException;
import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import com.project.real_estate_project03_team02.repository.business.LogRepository;
import com.project.real_estate_project03_team02.repository.business.*;
import com.project.real_estate_project03_team02.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service class responsible for resetting the database by removing all non-built-in data.
 */
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



/**
 * Resets the database by removing all non-built-in data.
 * This method removes all non-built-in data from various tables in the database, such as contacts,
 * favorites, logs, categories, districts, cities, countries, adverts, and users.
 * If any error occurs during the deletion process, a BadRequestException is thrown with an appropriate error message.
 *
 * The method proceeds to delete data from each repository in a transactional manner, ensuring consistency in the database.
 * Each deletion operation is wrapped in a try-catch block to handle potential exceptions.
 *
 * @throws BadRequestException if any error occurs during the deletion process, indicating that the data could not be deleted successfully.
 */
    @Transactional
    public void resetDatabase() {
        // Attempt to remove contact messages
        try {
            contactMessageRepository.removeAll();
        } catch (BadRequestException e) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_CONTACT_MESSAGES);
        }

        // Attempt to remove favorites
        try {
            favoritesRepository.removeAll();
        } catch (BadRequestException e) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_FAVORITES);
        }

        // Attempt to remove logs
        try {
            logRepository.removeAll();
        } catch (BadRequestException e) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_LOGS);
        }

        // Attempt to remove category property values
        try {
            categoryPropertyValueRepository.removeAll();
        } catch (BadRequestException e) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_CATEGORY_PROPERTY_VALUES);
        }

        // Attempt to delete non-built-in category property keys
        try {
            categoryPropertyKeyRepository.deleteAllByBuiltInIsFalse();
        } catch (BadRequestException e) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_CATEGORY_PROPERTY_KEYS);
        }

        // Attempt to remove tour requests
        try {
            tourRequestRepository.removeAll();
        } catch (BadRequestException e) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_TOUR_REQUESTS);
        }

        // Attempt to remove images
        try {
            imagesRepository.removeAll();
        } catch (BadRequestException e) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_IMAGES);
        }

        // Attempt to delete non-built-in adverts
        try {
            advertRepository.deleteAllByBuiltInIsFalse();
        } catch (BadRequestException e) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_ADVERTS);
        }

        // Attempt to delete non-built-in categories
        try {
            categoryRepository.deleteAllByBuiltInIsFalse();
        } catch (BadRequestException e) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_CATEGORIES);
        }

        // Attempt to remove districts
        try {
            districtRepository.removeAll();
        } catch (BadRequestException e) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_DISTRICTS);
        }

        // Attempt to remove cities
        try {
            cityRepository.removeAll();
        } catch (BadRequestException e) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_CITIES);
        }

        // Attempt to remove countries
        try {
            countryRepository.removeAll();
        } catch (BadRequestException e) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_COUNTRIES);
        }

        // Attempt to delete non-built-in advert types
        try {
            advertTypesRepository.deleteAllByBuiltInIsFalse();
        } catch (BadRequestException e) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_ADVERT_TYPES);
        }

        // Attempt to delete non-built-in users
        try {
            userRepository.deleteAllByBuiltInIsFalse();
        } catch (BadRequestException e) {
            throw new BadRequestException(ErrorMessages.NOT_DELETED_USERS);
        }

    }


}
