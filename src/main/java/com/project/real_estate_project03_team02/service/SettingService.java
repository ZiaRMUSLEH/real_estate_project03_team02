package com.project.real_estate_project03_team02.service;


import com.project.real_estate_project03_team02.repository.business.AdvertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SettingService {

    private final AdvertRepository advertRepository;
    //private final AdvertTypeRepository advertTypeRepository;
    //private final CategoryRepository categoryRepository;
//    private final AdvertRepository advertRepository;
//    private final AdvertRepository advertRepository;
//    private final AdvertRepository advertRepository;
//    private final AdvertRepository advertRepository;
//    private final AdvertRepository advertRepository;


    public void resetDatabase() {
    }
}
