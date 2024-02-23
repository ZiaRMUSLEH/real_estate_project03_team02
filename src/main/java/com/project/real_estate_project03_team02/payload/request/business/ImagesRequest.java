package com.project.real_estate_project03_team02.payload.request.business;


import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImagesRequest {

    private Long id;

    @Lob
    @Column(columnDefinition = "Oid")
    private byte[] data;

    @NotNull
    private String name;

    private String type;

    @NotNull
    private boolean featured;

}
