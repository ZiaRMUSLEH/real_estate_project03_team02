package com.project.real_estate_project03_team02.entity.concretes;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @NotNull
    public String userId;
//
//    @NotNull
//    public Advert advertId;

   @NotNull
    @JsonFormat(pattern = " yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date createAt;
}
