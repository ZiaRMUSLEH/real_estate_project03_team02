package com.project.real_estate_project03_team02.payload.mappers.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.entity.concretes.business.Category;
import com.project.real_estate_project03_team02.entity.concretes.business.CategoryPropertyKey;
import com.project.real_estate_project03_team02.payload.request.business.AdvertRequest;
import com.project.real_estate_project03_team02.payload.response.business.AdvertResponse;
import com.project.real_estate_project03_team02.service.business.CategoryPropertyValueService;
import com.project.real_estate_project03_team02.service.business.ImagesService;
import com.project.real_estate_project03_team02.service.business.TourRequestService;
import com.project.real_estate_project03_team02.service.helper.CategoryServiceHelper;
import com.project.real_estate_project03_team02.service.helper.SlugGenerator;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Component
@RequiredArgsConstructor
public class AdvertToAdvertResponseMapper {


    private final CategoryServiceHelper categoryServiceHelper;
    private final CategoryPropertyValueService categoryPropertyValueService;

    private final ImagesService imagesService;

    private final TourRequestService tourRequestService;

    private final SlugGenerator slugGenerator;



    public AdvertResponse mapAdvertToAdvertResponse(Advert advert) {
        Category category = advert.getCategoryId();
        List<CategoryPropertyKey> categoryPropertyKeys = categoryServiceHelper.findCategoryPropertyKeyByCategoryId(category);

        List<Map<String, Long>> properties = categoryPropertyKeys.stream().map(categoryPropertyKey -> {
                    CategoryPropertyKey key = categoryPropertyKey;
                    String name = key.getName();
                    Long value = Long.parseLong(categoryPropertyValueService.findByCategoryPropertyKey(key).getValue());
                    return Map.of(name, value);
                })
                .collect(Collectors.toList());

        return AdvertResponse.builder()
                .id(advert.getId())
                .title(advert.getTitle())
                .properties((ArrayList<Map<String, Long>>) properties)
                .images(imagesService.getImageDataByAdvertId(advert))
                .tourRequests(tourRequestService.findAllByAdvertId(advert))
                .build();
    }


//    public AdvertResponse mapAdvertToAdvertResponse(Advert advert) {
//        Category category = advert.getCategoryId();
//        List<CategoryPropertyKey> categoryPropertyKeys = categoryServiceHelper.findCategoryPropertyKeyByCategoryId(category);
//        List<String> categoryPropertyKeyNames = categoryPropertyKeys.stream()
//                .map(CategoryPropertyKey::getName)
//                .collect(Collectors.toList());
//
//        List<Long> categoryPropertyValueValues = categoryPropertyKeys.stream()
//                .map(key -> Long.parseLong(categoryPropertyValueService.findByCategoryPropertyKey(key).getValue()))
//                .collect(Collectors.toList());
//
//        ArrayList<Map<String, Long>> properties = new ArrayList<>();
//
//        for (int i = 0; i < categoryPropertyKeyNames.size(); i++) {
//            Map<String, Long> map = new TreeMap<>();
//            map.put(categoryPropertyKeyNames.get(i), categoryPropertyValueValues.get(i));
//            properties.add(map);
//        }
//
//        return AdvertResponse.builder()
//                .id(advert.getId())
//                .title(advert.getTitle())
//                .properties(properties)
//                .images(imagesService.getImageDataByAdvertId(advert))
//                .tourRequests(tourRequestService.findAllByAdvertId(advert))
//                .build();
//    }




}
