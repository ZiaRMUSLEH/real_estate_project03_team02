package com.project.real_estate_project03_team02.service.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Images;
import com.project.real_estate_project03_team02.exception.ResourceNotFoundException;
import com.project.real_estate_project03_team02.payload.mappers.business.ImagesMapper;
import com.project.real_estate_project03_team02.payload.request.business.ImagesRequest;
import com.project.real_estate_project03_team02.payload.response.business.ImagesResponse;
import com.project.real_estate_project03_team02.repository.business.ImagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImagesService {

private final ImagesRepository imagesRepository;
private final ImagesMapper imagesMapper;

    public byte[] getImageDataById(Long imageId){
        // Retrieve the image entity from the database based on the provided imageId
        Images images = imagesRepository.findById(imageId)
                .orElseThrow(() -> new ResourceNotFoundException("Image not found with ID: " + imageId));

        // Extract and return the image data from the image entity
        return images.getData();
    }

//   public Long saveImageForAdvert(Long advertId, MultipartFile image){
//        Images images=imagesRepository.save(advertId)
//                .orElseThrow(()->new ResourceNotFoundException("Image not found with advertId: "+advertId));
//
//        return List<saveImageForAdvert(advertId, images)>;
//   }

//    public java.util.List<Long> saveImages(java.util.List<MultipartFile> files) {
//
//        List<Long> imageIds = new ArrayList<>();
//        for (MultipartFile file : files) {
//            ImagesRequest imagesRequest = mapMultipartFileToImagesRequest(file);
//            Images image = imagesMapper.mapImagesRequestToImages(imagesRequest);
//            Images savedImage = imagesRepository.save(image);
//            imageIds.add(savedImage);
//        }
//        return imageIds;
//    }
//
//    private ImagesRequest mapMultipartFileToImagesRequest(MultipartFile file) {
//        // Implement logic to map MultipartFile to ImagesRequest
//    }
}
