package com.project.real_estate_project03_team02.controller.business;


import com.project.real_estate_project03_team02.payload.request.business.ContactRequest;
import com.project.real_estate_project03_team02.payload.response.business.ContactResponse;
import com.project.real_estate_project03_team02.service.business.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/contactmessages")
@RequiredArgsConstructor
public class ContactController {


 private final ContactService contactService;

    @GetMapping()
    public Page<ContactResponse> getAllContactMessages(@RequestParam(value = "page",defaultValue = "1")int page,
                                                       @RequestParam(value = "size",defaultValue = "10")int size,
                                                       @RequestParam(value = "sort",defaultValue = "category_id")String sort,
                                                       @RequestParam(value = "type",defaultValue = "asc")String asc){


        return contactService.getAllContactMessages(page,size,sort,asc);

    }




}
