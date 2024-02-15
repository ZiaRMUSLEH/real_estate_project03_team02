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

    @GetMapping("/pages")
    public Page<ContactResponse> getAllContactMessages(HttpServletRequest httpServletRequest,
                                                       @RequestParam(value = "page",defaultValue = "1")int page,
                                                       @RequestParam(value = "size",defaultValue = "10")int size,
                                                       @RequestParam(value = "sort",defaultValue = "category_id")String sort,
                                                       @RequestParam(value = "type",defaultValue = "asc")Sort.Direction asc){

        HttpServletRequest HttpServletRequest;
        return contactService.getAllContactMessages(HttpServletRequest,page,size,sort,asc);

    }


}
