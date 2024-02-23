package com.project.real_estate_project03_team02.controller.business;


import com.project.real_estate_project03_team02.payload.request.business.ContactMessageRequest;
import com.project.real_estate_project03_team02.payload.response.business.ContactMessageResponse;
import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.service.business.ContactMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/contactmessages")
@RequiredArgsConstructor
public class ContactMessageController {


 private final ContactMessageService contactMessageService;

    @GetMapping()
    public Page<ContactMessageResponse> getAllContactMessages(@RequestParam(value = "page",defaultValue = "1")int page,
                                                              @RequestParam(value = "size",defaultValue = "10")int size,
                                                              @RequestParam(value = "sort",defaultValue = "category_id")String sort,
                                                              @RequestParam(value = "type",defaultValue = "asc")String asc){


        return contactMessageService.getAllContactMessages(page,size,sort,asc);

    }

    @PostMapping("/save")
    public ResponseEntity<ContactMessageResponse>saveContactMessage(@RequestBody @Valid ContactMessageRequest contactMessageRequest){

        return ResponseEntity.ok(contactMessageService.saveContactMessage(contactMessageRequest));
    }


}
