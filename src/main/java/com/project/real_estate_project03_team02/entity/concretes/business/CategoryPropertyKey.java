package com.project.real_estate_project03_team02.entity.concretes.business;



import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "category_property_keys")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPropertyKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 2, max = 80)
    private String name;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Column(columnDefinition = "boolean default false")
    private boolean builtIn;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category categoryId;


}