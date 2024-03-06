package com.project.real_estate_project03_team02.payload.response.business;


import lombok.*;

/**
 * The AdvertResponseForFavorites class represents the response data structure for favorite adverts.
 * It contains essential information about an advert, such as its ID and title.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdvertResponseForFavorites {

    /**
     * The unique identifier for the advert.
     */
    private Long id;

    /**
     * The title of the advert.
     */
    private String title;
}
