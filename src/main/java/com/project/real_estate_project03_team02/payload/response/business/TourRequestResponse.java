package com.project.real_estate_project03_team02.payload.response.business;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.entity.concretes.user.User;
import lombok.*;
import java.time.LocalDate;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TourRequestResponse {


    private Long id;

    private LocalDate tourDate;

    private Advert advertId;

    private User ownerUserId;

    private User guestUserId;


}
