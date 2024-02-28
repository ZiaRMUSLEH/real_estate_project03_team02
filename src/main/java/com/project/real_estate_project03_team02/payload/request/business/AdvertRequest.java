package com.project.real_estate_project03_team02.payload.request.business;

import com.project.real_estate_project03_team02.entity.concretes.business.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdvertRequest {

    @NotNull(message = "Title must not be empty")
    private String title;

    @Size(max=300, message = "Description should be at most 300 chars")
    private String description;

    @NotNull(message = "Price must not be empty")
    private Double price;

    @NotNull(message = "Advert Type Id must not be empty")
    private AdvertType advertTypeId;

    @NotNull(message = "Country Id must not be empty")
    private Country countryId;

    @NotNull(message = "City Id must not be empty")
    private City cityId;

    @NotNull(message = "District Id must not be empty")
    private District districtId;

    @NotNull(message = "Category Id must not be empty")
    private Category categoryId;

    @NotNull(message = "Please enter isActive")
    private Boolean isActive;

    private ArrayList<String> properties;








}
