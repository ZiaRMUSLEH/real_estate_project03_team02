package com.project.real_estate_project03_team02.payload.response.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Images;
import com.project.real_estate_project03_team02.entity.concretes.business.TourRequest;
import lombok.*;

import java.util.ArrayList;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdvertResponse {

    private Long id;

    private String title;

    private ArrayList<Map<String, Long>> properties;

    private ArrayList<Images> images;

    private ArrayList<TourRequest> tourRequests;

}
