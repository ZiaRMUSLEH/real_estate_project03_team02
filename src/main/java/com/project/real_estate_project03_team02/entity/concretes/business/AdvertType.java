package com.project.real_estate_project03_team02.entity.concretes.business;

import com.project.real_estate_project03_team02.entity.enums.AdvertTypes;
import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "advert_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdvertType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;



    @Column(nullable = false,length = 30)
    @Enumerated(EnumType.STRING)
    private AdvertTypes title;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Column(columnDefinition = "boolean default false")
    private boolean builtIn;









}
