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


import java.io.IOException;
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



    public List<Long> saveImagesForAdvertisement(Long advertId, List<MultipartFile> files) {
        List<Long> savedImageIds = new ArrayList<>();

        for (MultipartFile file : files) {
            ImagesRequest imagesRequest = convertMultipartFileToImagesRequest(file);
            Images images = imagesMapper.mapImagesRequestToImages(imagesRequest);
            images.setAdvertId(advertId); // Associate the image with the provided advertisement ID
            Images savedImages = imagesRepository.save(images);
            savedImageIds.add(savedImages.getId());
        }

        return savedImageIds;
    }

    private ImagesRequest convertMultipartFileToImagesRequest(MultipartFile file) {
        ImagesRequest imagesRequest = new ImagesRequest();
        imagesRequest.setName(file.getOriginalFilename());
        imagesRequest.setType(file.getContentType());
        try {
            imagesRequest.setData(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception appropriately
        }
        return imagesRequest;
    }

















//    public List<Long> saveImages(List<MultipartFile> files) {
//        List<Long> savedImageIds = new ArrayList<>();
//        for (MultipartFile file : files) {
//            // Convert MultipartFile to ImagesRequest
//            ImagesRequest imagesRequest = convertMultipartFileToImagesRequest(file);
//            // Map ImagesRequest to Images
//            Images images = imagesMapper.mapImagesRequestToImages(imagesRequest);
//            // Save Images object and retrieve its ID
//            Images savedImages = imagesRepository.save(images);
//            savedImageIds.add(savedImages.getId());
//        }
//        return savedImageIds;
//    }
//
//    private ImagesRequest convertMultipartFileToImagesRequest(MultipartFile file) {
//        ImagesRequest imagesRequest = new ImagesRequest();
//        // Set necessary fields in ImagesRequest, like name, type, data, etc.
//        // For example:
//        imagesRequest.setName(file.getOriginalFilename());
//        imagesRequest.setType(file.getContentType());
//        // Convert file bytes to byte array and set in ImagesRequest
//        try {
//            imagesRequest.setData(file.getBytes());
//        } catch (IOException e) {
//            // Handle exception
//            e.printStackTrace();
//        }
//        return imagesRequest;
//    }

  }
