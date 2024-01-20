package com.project.real_estate_project03_team02.entity.concretes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "adverts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private String desc;

    @NotNull
    @Size(min = 5, max = 200)
    @Column(length = 200)
    private String slug;

    @NotNull
    private Double price;

    @NotNull
    @Column(columnDefinition = "int default 0")
    private int status;

    @NotNull
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Column(columnDefinition = "boolean default false")
    private boolean built_in;

    @NotNull
    @Column(columnDefinition = "boolean default true")
    private boolean is_active;

    @NotNull
    @Column(columnDefinition = "int default 0")
    private int view_count;

    private String location;

    @NotNull
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "advert_type_id")
    private AdvertType advert_type_id;

    @NotNull
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id")
    private Country country_id;

    @NotNull
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private City city_id;

    @NotNull
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "district_id")
    private District district_id;

    @NotNull
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user_id;

    @NotNull
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category_id;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime create_at;

    private LocalDateTime update_at;













}