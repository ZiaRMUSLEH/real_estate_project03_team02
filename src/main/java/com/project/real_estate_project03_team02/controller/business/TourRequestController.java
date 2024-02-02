package com.project.real_estate_project03_team02.controller.business;


import com.project.real_estate_project03_team02.payload.request.business.TourRequestRequest;
import com.project.real_estate_project03_team02.payload.response.business.TourRequestResponse;
import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.service.business.TourRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/tour-requests")
@RequiredArgsConstructor
public class TourRequestController {

    private final TourRequestService tourRequestService;



    @GetMapping("/auth") //http://localhost:8080/tour-requests/auth
    public Page<TourRequestResponse> getAllTourRequestOfAuthenticatedUser(HttpServletRequest httpServletRequest,
                                                                          @RequestParam(value = "page",defaultValue = "0") int page,
                                                                          @RequestParam(value = "size",defaultValue = "20") int size,
                                                                          @RequestParam(value = "sort",defaultValue = "category_id") String sort,
                                                                          @RequestParam(value = "type",defaultValue = "asc") String type){
        return tourRequestService.getAllTourRequestOfAuthenticatedUser(httpServletRequest,page,size,sort,type);
    }


    @PostMapping() //http://localhost:8080/tour-requests
    public ResponseMessage<TourRequestResponse> saveTourRequest( HttpServletRequest httpServletRequest,@Valid @RequestBody TourRequestRequest tourRequestRequest){
        return tourRequestService.saveTourRequest(httpServletRequest,tourRequestRequest);
    }


    @GetMapping("/admin") //http://localhost:8080/tour-requests/admin
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public Page<TourRequestResponse> getAllTourRequests(                  @RequestParam(value = "page",defaultValue = "0") int page,
                                                                          @RequestParam(value = "size",defaultValue = "20") int size,
                                                                          @RequestParam(value = "sort",defaultValue = "category_id") String sort,
                                                                          @RequestParam(value = "type",defaultValue = "asc") String type	){
        return tourRequestService.getAllTourRequests(page,size,sort,type);
    }


    @GetMapping("/{id}/auth")  //http://localhost:8080/tour-requests/1/auth
    public ResponseEntity<TourRequestResponse> getTourRequestDetail( @PathVariable Long id, HttpServletRequest httpServletRequest){
        return tourRequestService.getTourRequestDetail(httpServletRequest,id);
    }


    @GetMapping("/{id}/admin")  //http://localhost:8080/tour-requests/1/admin
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<TourRequestResponse> getTourRequestDetailById( @PathVariable Long id){
        return tourRequestService.getTourRequestDetailById(id);
    }










}
