package com.project.real_estate_project03_team02.service.business;

import com.project.real_estate_project03_team02.entity.concretes.business.City;
import com.project.real_estate_project03_team02.repository.business.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * The CityService class provides business logic operations related to cities in the real estate project.
 * It facilitates interactions with the database through the CityRepository.
 * This class is annotated with Spring's @Service annotation to indicate that it's a service component managed by Spring.
 * It also utilizes Lombok's @RequiredArgsConstructor to automatically generate a constructor injecting the dependencies, specifically CityRepository.
 */
@Service
@RequiredArgsConstructor
public class CityService {

    /**
     * The CityRepository instance is used to perform CRUD operations on City entities in the database.
     */
    private final CityRepository cityRepository;

    public ArrayList<City> getCities() {
        return (ArrayList<City>) cityRepository.findAll();
    }

    // Additional methods and business logic can be implemented here
}
