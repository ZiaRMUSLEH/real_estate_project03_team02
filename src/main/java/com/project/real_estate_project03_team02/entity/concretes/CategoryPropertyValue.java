package com.project.real_estate_project03_team02.entity.concretes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "category_property_value")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPropertyValue {
//has this went through?
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotNull(message = "Cannot be empty")
    @Size(max = 80)
    String value;

    @ManyToOne
    @JoinColumn(name = "advert_id",nullable = false)
    private Advert advert;

    @ManyToOne
    @JoinColumn(name = "category property_key_id",nullable = false)
    private CategoryPropertyKey categoryPropertyKey;

}
