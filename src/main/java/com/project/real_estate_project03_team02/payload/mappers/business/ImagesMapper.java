package com.project.real_estate_project03_team02.payload.mappers.business;

import com.project.real_estate_project03_team02.entity.concretes.business.ContactMessage;
import com.project.real_estate_project03_team02.entity.concretes.business.Images;
import com.project.real_estate_project03_team02.payload.request.business.ContactMessageRequest;
import com.project.real_estate_project03_team02.payload.request.business.ImagesRequest;
import com.project.real_estate_project03_team02.payload.response.business.ContactMessageResponse;
import com.project.real_estate_project03_team02.payload.response.business.ImagesResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ImagesMapper {

    public Images mapImagesRequestToImages (ImagesRequest imagesRequest){
        return Images.builder()
                .type(imagesRequest.getType())
                .name(imagesRequest.getName())
                .data(imagesRequest.getData())
                .featured(imagesRequest.isFeatured())
                .build();

    }

    public ImagesResponse mapImagesToImagesResponse(Images images){
        return ImagesResponse.builder()
                .id(images.getId())
                .data(images.getData())
                .type(images.getType())
                .name(images.getName())
                .featured(images.isFeatured())
                .build();
    }
}
