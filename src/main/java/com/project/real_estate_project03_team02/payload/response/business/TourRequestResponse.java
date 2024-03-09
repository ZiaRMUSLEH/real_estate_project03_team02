package com.project.real_estate_project03_team02.payload.response.business;


import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.entity.concretes.user.User;
import lombok.*;
import java.time.LocalDate;



/**
 * The TourRequestResponse class represents a response object for a tour request in the real estate project.
 * It contains information about the tour request, including its unique identifier, tour date, associated advert,
 * owner user, and guest user.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourRequestResponse {

    /**
     * The unique identifier of the tour request.
     */
    private Long id;

    /**
     * The date of the tour.
     */
    private LocalDate tourDate;

    /**
     * The advertisement associated with the tour request.
     */
    private Advert advertId;

    /**
     * The owner user who created the advertisement.
     */
    private User ownerUserId;

    /**
     * The guest user who requested the tour.
     */
    private User guestUserId;
}

