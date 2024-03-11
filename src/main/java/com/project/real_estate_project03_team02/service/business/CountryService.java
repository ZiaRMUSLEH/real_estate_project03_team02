package com.project.real_estate_project03_team02.service.business;

import com.project.real_estate_project03_team02.repository.business.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
