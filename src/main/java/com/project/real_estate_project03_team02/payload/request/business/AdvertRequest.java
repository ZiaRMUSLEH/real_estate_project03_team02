package com.project.real_estate_project03_team02.payload.request.business;

import com.project.real_estate_project03_team02.entity.concretes.business.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdvertRequest {

    @NotNull(message = "Title must not be empty")
    @Size(min = 5, max = 150,message = "Your title should be at least 5 chars")
    private String title;


    @Size( max = 300,message = "Your description should be less than 300 chars")
    private String description;

    @NotNull(message = "Price must not be empty")
    private Double price;

    @NotNull(message = "Advert Type Id must not be empty")
    private Long advertTypeId;

    @NotNull(message = "Country Id must not be empty")
    private Long countryId;

    @NotNull(message = "City Id must not be empty")
    private Long cityId;

    @NotNull(message = "District Id must not be empty")
    private Long districtId;

    @NotNull(message = "Category Id must not be empty")
    private Long categoryId;

    //@NotNull(message = "Please enter isActive")
    //private Boolean isActive;

    //enum olustur, key_id yazmak icin.
    private ArrayList<Map<Long,String>> properties;
=======

    @NotNull(message = "Images must not be empty")
    private Images images;



    private ArrayList<Map<Long, String>> properties;


    @NotNull(message = "Location must not be empty")
    private String location;
>>>>>>> main








}
