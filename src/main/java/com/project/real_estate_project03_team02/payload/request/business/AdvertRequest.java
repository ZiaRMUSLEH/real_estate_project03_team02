/**
 * This class represents a request payload for creating or updating an advertisement in a real estate project.
 * It contains various fields representing the attributes of an advertisement, such as title, description, price, location, and others.
 * Each field is annotated with validation constraints to ensure data integrity and consistency.
 *
 */
package com.project.real_estate_project03_team02.payload.request.business;

import com.project.real_estate_project03_team02.entity.concretes.business.AdvertType;
import com.project.real_estate_project03_team02.entity.concretes.business.Country;
import com.project.real_estate_project03_team02.entity.concretes.business.City;
import com.project.real_estate_project03_team02.entity.concretes.business.District;
import com.project.real_estate_project03_team02.entity.concretes.business.Category;
import com.project.real_estate_project03_team02.entity.concretes.business.Images;
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

    /**
     * The title of the advertisement.
     */
    @NotNull(message = "Title must not be empty")
    @Size(min = 5, max = 150,message = "Your title should be at least 5 characters")
    private String title;

    /**
     * The description of the advertisement.
     */
    @Size(max = 300,message = "Your description should be less than 300 characters")
    private String description;

    /**
     * The price of the advertisement.
     */
    @NotNull(message = "Price must not be empty")
    private Double price;

    /**
     * The type of advertisement.
     */
    @NotNull(message = "Advert Type Id must not be empty")
    private AdvertType advertTypeId;

    /**
     * The country ID where the property is located.
     */
    @NotNull(message = "Country Id must not be empty")
    private Country countryId;

    /**
     * The city ID where the property is located.
     */
    @NotNull(message = "City Id must not be empty")
    private City cityId;

    /**
     * The district ID where the property is located.
     */
    @NotNull(message = "District Id must not be empty")
    private District districtId;

    /**
     * The category ID of the property.
     */
    @NotNull(message = "Category Id must not be empty")
    private Category categoryId;

    /**
     * The images associated with the advertisement.
     */
    @NotNull(message = "Images must not be empty")
    private Images images;

    /**
     * The properties associated with the advertisement.
     * It is represented as a list of key-value pairs where the key is the property ID and the value is the property value.
     */
    @NotNull(message = "Properties must not be empty")
    private ArrayList<Map<Long,String>> properties;

    /**
     * The location of the property.
     */
    @NotNull(message = "Location must not be empty")
    private String location;
}
