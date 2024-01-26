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



    @NotNull(message = "Tour Date must not be empty")

    private LocalDate tourDate;
    @NotNull(message = "Tour Time must not be empty")
    private LocalTime tourTime;
    @NotNull(message = "Advert Id must not be empty")
    private Long advertId;






}
