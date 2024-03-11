package com.project.real_estate_project03_team02.service.business;

import com.project.real_estate_project03_team02.repository.business.DistrictRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * The DistrictService class is responsible for handling business logic related to districts in the real estate project.
 * It acts as an intermediary between the controller layer and the repository layer, facilitating operations on districts.
 * This service class primarily interacts with the DistrictRepository for data access and manipulation.
 *
 */
@Service
@RequiredArgsConstructor
public class DistrictService {

    private final DistrictRepository districtRepository;


}
