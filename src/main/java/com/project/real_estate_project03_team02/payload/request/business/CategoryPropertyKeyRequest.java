package com.project.real_estate_project03_team02.payload.request.business;
import lombok.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryPropertyKeyRequest {
    @NotNull
    @Size(min = 2, max = 80)
    private String name;
}
