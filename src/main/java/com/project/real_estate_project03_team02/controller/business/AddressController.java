package com.project.real_estate_project03_team02.controller.business;

import com.project.real_estate_project03_team02.entity.concretes.business.City;
import com.project.real_estate_project03_team02.entity.concretes.business.Country;
import com.project.real_estate_project03_team02.entity.concretes.business.District;
import com.project.real_estate_project03_team02.service.business.CityService;
import com.project.real_estate_project03_team02.service.business.CountryService;
import com.project.real_estate_project03_team02.service.business.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class AddressController {

    private final CountryService countryService;
    private final CityService cityService;
    private final DistrictService districtService;


    @GetMapping("/countries")
    ArrayList<Country> getCountries(){
        return countryService.getCountries();
    }
    @GetMapping("/cities")
    ArrayList<City> getCities(){
        return cityService.getCities();
    }

    @GetMapping("/districts")
    ArrayList<District> getDistricts(){
        return districtService.getDistricts();
    }







}
