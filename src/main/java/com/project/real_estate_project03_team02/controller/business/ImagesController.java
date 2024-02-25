package com.project.real_estate_project03_team02.controller.business;

import com.project.real_estate_project03_team02.payload.request.business.ImagesRequest;
import com.project.real_estate_project03_team02.service.business.AdvertService;
import com.project.real_estate_project03_team02.service.business.ImagesService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImagesController {

    private final AdvertService advertService;
    private final ImagesService imagesService;


    @GetMapping("/{imageId}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long imageId) {
        // Retrieve the image data based on the provided image ID
        byte[] imageData = imagesService.getImageDataById(imageId);

        // If image data is found, return it in the response with appropriate headers
        if (imageData != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // Assuming images are JPEG format

            // You can add additional headers if needed

            // Return the image data along with headers in the ResponseEntity
            return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
        } else {
            // If image data is not found, return 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }
//    @PostMapping("/{advertId}")
//    public ResponseEntity<List<Long>> uploadImagesForAdvert(@PathVariable Long advertId,
//                                                            @RequestParam("images") List<MultipartFile> images) {
//        // Validate if the advertId exists and handle any errors
//
//        // Validate if images are provided
//        if (images == null || images.isEmpty()) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        // Process each image and store them in the database
//        List<Long> imageIds = new ArrayList<>();
//        for (MultipartFile image : images) {
//            ImagesRequest imagesRequest = mapMultipartFileToImagesRequest(image);
//            Long imageId = imagesService.saveImageForAdvert(advertId, image);
//            imageIds.add(imageId);
//        }
//
//        // Return the list of imageIds in the response
//        return ResponseEntity.ok(imageIds);
    //}

//    @PostMapping
//    public ResponseEntity<List<Long>> uploadImages(@RequestParam("files") List<MultipartFile> files) {
//        List<Long> imageIds = imagesService.saveImages(files);
//        return ResponseEntity.ok(imageIds);
//    }


}
