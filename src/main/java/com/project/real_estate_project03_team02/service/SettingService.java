package com.project.real_estate_project03_team02.service;


import com.project.real_estate_project03_team02.exception.BadRequestException;
import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import com.project.real_estate_project03_team02.repository.business.AdvertTypeRepository;
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
    private final AdvertTypeRepository advertTypeRepository;
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




//    public void resetDatabase() {
//        try {advertRepository.deleteAll();} catch (BadRequestException e) {throw new BadRequestException(ErrorMessages.NOT_DELETED_ADVERTS);}
//        try {advertTypeRepository.deleteAll();} catch (BadRequestException e) {throw new BadRequestException(ErrorMessages.NOT_DELETED_ADVERT_TYPES);}
//        try {categoryRepository.deleteAll();} catch (BadRequestException e) {throw new BadRequestException(ErrorMessages.NOT_DELETED_CATEGORIES);}
//        try {categoryPropertyKeyRepository.deleteAll();} catch (BadRequestException e) {throw new BadRequestException(ErrorMessages.NOT_DELETED_CATEGORY_PROPERTY_KEYS);}
//        try {categoryPropertyValueRepository.deleteAll();} catch (BadRequestException e) {throw new BadRequestException(ErrorMessages.NOT_DELETED_CATEGORY_PROPERTY_VALUES);}
//        try {cityRepository.deleteAll();} catch (BadRequestException e) {throw new BadRequestException(ErrorMessages.NOT_DELETED_CITIES);}
//        try {contactMessageRepository.deleteAll();} catch (BadRequestException e) {throw new BadRequestException(ErrorMessages.NOT_DELETED_CONTACT_MESSAGES);}
//        try {countryRepository.deleteAll();} catch (BadRequestException e) {throw new BadRequestException(ErrorMessages.NOT_DELETED_COUNTRIES);}
//        try {districtRepository.deleteAll();} catch (BadRequestException e) {throw new BadRequestException(ErrorMessages.NOT_DELETED_DISTRICTS);}
//        try {favoritesRepository.deleteAll();} catch (BadRequestException e) {throw new BadRequestException(ErrorMessages.NOT_DELETED_FAVORITES);}
//        try {imagesRepository.deleteAll();} catch (BadRequestException e) {throw new BadRequestException(ErrorMessages.NOT_DELETED_IMAGES);}
//        try {logRepository.deleteAll();} catch (BadRequestException e) {throw new BadRequestException(ErrorMessages.NOT_DELETED_LOGS);}
//        try {tourRequestRepository.deleteAll();} catch (BadRequestException e) {throw new BadRequestException(ErrorMessages.NOT_DELETED_TOUR_REQUESTS);}
//        try {userRepository.deleteAll();} catch (BadRequestException e) {throw new BadRequestException(ErrorMessages.NOT_DELETED_USERS);}
//        try {userRoleRepository.deleteAll();} catch (BadRequestException e) {throw new BadRequestException(ErrorMessages.NOT_DELETED_ROLES);}
//    }

    @Transactional
    public String resetDatabase() {
        advertRepository.deleteAllWhichBuiltInIsFalse();
        if(advertRepository.countWhichBuiltInIsFalse()!=0){
            throw new BadRequestException(ErrorMessages.NOT_DELETED_ADVERTS);
        }

        return "Database Reset Successfully";
    }

}
