package com.project.real_estate_project03_team02.controller.business;

import com.project.real_estate_project03_team02.payload.request.business.AdvertTypesRequest;
import com.project.real_estate_project03_team02.payload.response.business.AdvertTypesResponse;
import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.service.business.AdvertTypesService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/advert-types")
@AllArgsConstructor
public class AdvertTypesController {

    private AdvertTypesService advertTypesService;

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public List<AdvertTypesResponse> getAllAdvertTypes(){
        return advertTypesService.getAllAdvertTypes();
    }

//    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('MANAGER')")
//    public ResponseMessage<AdvertTypesResponse> getAdvertTypesById(@PathVariable Long id){
//        return advertTypesService.getAdvertTypesById(id);
//    }
//
//    @PostMapping()
//    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('MANAGER')")
//    public ResponseMessage<AdvertTypesResponse> saveAdvertType(@Valid @RequestBody AdvertTypesRequest advertTypesRequest){
//        return advertTypesService.saveAdvertTypes(advertTypesRequest);
//    }






//    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('MANAGER')")
//    public ResponseMessage deleteAdvertTypeById(@PathVariable Long id){return AdvertTypesService.deleteAdvertTypeById(id);}

}


