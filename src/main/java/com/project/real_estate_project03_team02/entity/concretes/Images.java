package com.project.real_estate_project03_team02.entity.concretes;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Lob
    @Column(name="data",columnDefinition = "Oid")
    public byte[] data;

    @NotNull
    public String name;

    public String type;

    @NotNull
    private boolean featured=false;


//    @NotNull
//    public Advert advert_id;

}
