package com.project.real_estate_project03_team02.entity.concretes.business;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "category_property_values")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPropertyValue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Cannot be empty")
    @Size(max = 80)
    private String value;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "advert_id",nullable = false)
    private Advert advert;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_property_key_id",nullable = false)
    private CategoryPropertyKey categoryPropertyKey;


}
