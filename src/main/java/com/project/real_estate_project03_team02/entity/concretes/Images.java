package com.project.real_estate_project03_team02.entity.concretes;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "images")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    @Column(columnDefinition = "Oid")
    private byte[] data;

    @NotNull
    private String name;

    private String type;

    @NotNull
    private boolean featured;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "advert_id",nullable = false)
    @Setter(AccessLevel.NONE)
    private Advert advertId;


}
