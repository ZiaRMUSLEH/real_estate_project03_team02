package com.project.real_estate_project03_team02.entity.concretes.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.real_estate_project03_team02.entity.concretes.user.User;
import com.project.real_estate_project03_team02.entity.enums.AdvertStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Size(min = 5, max = 150)
    @Column(nullable = false,length = 150)
    private String title;

    @Size(max=300)
    @Column(length = 300)
    private String description;


    @Size(min = 5, max = 200)
    @Column(nullable = false,length = 200)
    private String slug;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false,columnDefinition = "integer default 0")
    @Enumerated(EnumType.ORDINAL)
    private AdvertStatus status;


    //@Getter(AccessLevel.NONE)
    //@Setter(AccessLevel.NONE)
    @Column(nullable = false,name = "built_in", columnDefinition = "boolean default false")
    private boolean builtIn;

    @Column(nullable = false,name= "is_active", columnDefinition = "boolean default true")
    private boolean isActive;

    @Column(nullable = false,name = "view_count", columnDefinition = "int default 0")
    private Integer viewCount;

    private String location;


    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false,name = "advert_type_id")
    private AdvertType advertTypeId;


    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false,name = "country_id")
    private Country countryId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false,name = "city_id")
    private City cityId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false,name = "district_id")
    private District districtId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false,name = "user_id")
    private User userId;


    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false,name = "category_id")
    private Category categoryId;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}