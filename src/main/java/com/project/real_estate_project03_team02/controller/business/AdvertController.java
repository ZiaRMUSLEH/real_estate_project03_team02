package com.project.real_estate_project03_team02.controller.business;

import com.project.real_estate_project03_team02.payload.request.business.AdvertRequest;
import com.project.real_estate_project03_team02.payload.response.business.AdvertResponse;
import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.service.business.AdvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/adverts")
@RequiredArgsConstructor
public class AdvertController {

    private final AdvertService advertService;

    @PostMapping("/save")
    public ResponseMessage<AdvertResponse>save(@RequestBody @Valid AdvertRequest advertRequest) {
        return advertService.save(advertRequest);
    }


}
