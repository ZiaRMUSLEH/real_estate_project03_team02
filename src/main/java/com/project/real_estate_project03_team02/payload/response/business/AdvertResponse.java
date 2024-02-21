package com.project.real_estate_project03_team02.payload.response.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Images;
import com.project.real_estate_project03_team02.entity.concretes.business.TourRequest;
import lombok.*;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdvertResponse {

    private Long id;

    private String title;

    private Images images;

    private TourRequest tour_requests;

    private ArrayList<String> properties;
}
