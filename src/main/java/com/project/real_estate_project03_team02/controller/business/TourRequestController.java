package com.project.real_estate_project03_team02.controller.business;


import com.project.real_estate_project03_team02.payload.request.business.TourRequestRequest;
import com.project.real_estate_project03_team02.payload.response.business.TourRequestResponse;
import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.service.business.TourRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/tour-requests")
@RequiredArgsConstructor
public class TourRequestController {

    private final TourRequestService tourRequestService;



    @GetMapping("/auth")
    public Page<TourRequestResponse> getAllTourRequestOfAuthenticatedUser(HttpServletRequest httpServletRequest,
                                                                          @RequestParam(value = "page",defaultValue = "0") int page,
                                                                          @RequestParam(value = "size",defaultValue = "20") int size,
                                                                          @RequestParam(value = "sort",defaultValue = "category_id") String sort,
                                                                          @RequestParam(value = "type",defaultValue = "asc") String type	){
        return tourRequestService.getAllTourRequestOfAuthenticatedUser(httpServletRequest,page,size,sort,type);
    }


    //TODO tests..

    @PostMapping()
    public ResponseMessage<TourRequestResponse> saveTourRequest( HttpServletRequest httpServletRequest,@Valid @RequestBody TourRequestRequest tourRequestRequest){
        return tourRequestService.saveTourRequest(httpServletRequest,tourRequestRequest);
    }



}