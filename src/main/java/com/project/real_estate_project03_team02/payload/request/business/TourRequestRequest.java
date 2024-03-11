/**
 * This class represents a request payload for scheduling a tour of a real estate property.
 * It contains necessary information such as tour date, tour time, and advertisement ID.
 */
package com.project.real_estate_project03_team02.payload.request.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourRequestRequest {

    /**
     * The date for the scheduled tour of the real estate property.
     */
    @NotNull(message = "Tour Date must not be empty")
    private LocalDate tourDate;

    /**
     * The time for the scheduled tour of the real estate property.
     */
    @NotNull(message = "Tour Time must not be empty")
    private LocalTime tourTime;

    /**
     * The ID of the advertisement for the real estate property.
     */
    @NotNull(message = "Advert Id must not be empty")
    private Long advertId;
}
