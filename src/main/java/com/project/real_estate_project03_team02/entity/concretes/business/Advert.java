package com.project.real_estate_project03_team02.entity.concretes.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.real_estate_project03_team02.entity.concretes.user.User;
import com.project.real_estate_project03_team02.entity.enums.AdvertStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Table(name = "adverts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Advert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5, max = 150)
    @Column(length = 150)
    private String title;

    @Size(max=300)
    @Column(length = 300)
    private String description;

    @NotNull
    @Size(min = 5, max = 200)
    @Column(length = 200)
    private String slug;

    @NotNull
    private Double price;

    @NotNull
    @Column(columnDefinition = "integer default 0")
    @Enumerated(EnumType.ORDINAL)
    private AdvertStatus status;

    @NotNull
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Column(name = "built_in", columnDefinition = "boolean default false")
    private Boolean builtIn;

    @NotNull
    @Column(name= "is_active", columnDefinition = "boolean default true")
    private Boolean isActive;

    @NotNull
    @Column(name = "view_count", columnDefinition = "int default 0")
    private Integer viewCount;

    private String location;

    @NotNull
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "advert_type_id")
    private AdvertType advertTypeId;

    @NotNull
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id")
    private Country countryId;

    @NotNull
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private City cityId;

    @NotNull
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "district_id")
    private District districtId;

    @NotNull
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User userId;

    @NotNull
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category categoryId;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    //private ArrayList<String> properties;

    //private Images images;

    //private TourRequest tourRequests;













}