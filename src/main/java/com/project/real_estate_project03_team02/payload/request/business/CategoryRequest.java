package com.project.real_estate_project03_team02.payload.request.business;

import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRequest {

    @NotNull
    @Size(max = 150, message = ErrorMessages.CATEGORY_MAX_LENGTH_TITLE)

    private String title;
    @NotNull
    @Size(max = 50, message = ErrorMessages.CATEGORY_MAX_LENGTH_ICON)
    private String icon;




}
