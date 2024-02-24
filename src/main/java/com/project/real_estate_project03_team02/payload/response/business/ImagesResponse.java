package com.project.real_estate_project03_team02.payload.response.business;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImagesResponse {

    private Long id;

    private byte[] data;

    private String name;

    private String type;

    private boolean featured;
}
