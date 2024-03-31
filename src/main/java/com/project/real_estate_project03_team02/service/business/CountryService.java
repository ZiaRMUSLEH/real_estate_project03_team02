package com.project.real_estate_project03_team02.service.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Country;
import com.project.real_estate_project03_team02.exception.ConflictException;
import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import com.project.real_estate_project03_team02.repository.business.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * The CountryService class is responsible for providing business logic related to countries
 * in the real estate project. It acts as a bridge between the controller layer and the
 * data access layer, handling operations such as retrieving country data from the repository.
 *
 */
@Service
@RequiredArgsConstructor
public class CountryService {

    /**
     * The CountryRepository dependency is automatically injected by Spring
     * through constructor injection. It provides methods to access and manipulate
     * country data in the database.
     */
    private final CountryRepository countryRepository;

    public ArrayList<Country> getCountries() {
        return (ArrayList<Country>) countryRepository.findAll();
    }

    public void save(Country country) {
        if(!countryRepository.existsByName(country.getName())) {
            countryRepository.save(country);
        }
    }
}
