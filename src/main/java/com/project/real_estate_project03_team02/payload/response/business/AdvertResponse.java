package com.project.real_estate_project03_team02.payload.response.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Images;
import com.project.real_estate_project03_team02.entity.concretes.business.TourRequest;
import lombok.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Represents the response payload for an advertisement.
 * This class contains information about a specific advertisement, including its title,
 * properties, images, and tour requests.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdvertResponse {

    /**
     * The unique identifier of the advertisement.
     */
    private Long id;

    /**
     * The title of the advertisement.
     */
    private String title;

    /**
     * The properties associated with the advertisement.
     * Each property is represented as a mapping between property names and their corresponding values.
     */
    private Set<Map<String, Long>> properties;

    /**
     * The images associated with the advertisement.
     * Each image is represented by an instance of the Images class.
     */
    private Set<Images> images;

    /**
     * The tour requests associated with the advertisement.
     * Each tour request is represented by an instance of the TourRequest class.
     */
    private Set<TourRequest> tourRequests;

}

