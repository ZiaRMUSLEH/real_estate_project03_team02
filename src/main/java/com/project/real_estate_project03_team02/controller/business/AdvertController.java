package com.project.real_estate_project03_team02.controller.business;

import com.project.real_estate_project03_team02.entity.concretes.business.AdvertType;
import com.project.real_estate_project03_team02.entity.concretes.business.Category;
import com.project.real_estate_project03_team02.payload.request.business.AdvertRequest;
import com.project.real_estate_project03_team02.payload.response.business.AdvertResponse;
import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.service.business.AdvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/adverts")
@RequiredArgsConstructor
public class AdvertController {

    private final AdvertService advertService;

    @PostMapping()
    public ResponseMessage<AdvertResponse>save(HttpServletRequest httpServletRequest, @RequestBody @Valid AdvertRequest advertRequest) {
        return advertService.save(httpServletRequest, advertRequest);
    }

    /**
     * Retrieves all adverts associated with the authenticated user.
     *
     * @param httpServletRequest The HttpServletRequest object containing the request information.
     * @param page               The page number for pagination. Default is 0.
     * @param size               The size of each page. Default is 20.
     * @param sort               The field to sort by. Default is 'category_id'.
     * @param type               The sorting order, either 'asc' (ascending) or 'desc' (descending). Default is 'asc'.
     * @return A Page object containing AdvertResponse instances representing adverts of the authenticated user.
     */
    @GetMapping("/auth")
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public Page<AdvertResponse> getAllAdvertOfAuthenticatedUser(HttpServletRequest httpServletRequest,
                                                                @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                                @RequestParam(value = "size", defaultValue = "20", required = false) int size,
                                                                @RequestParam(value = "sort", defaultValue = "category_id", required = false) String sort,
                                                                @RequestParam(value = "type", defaultValue = "asc", required = false) String type) {
        return advertService.getAllAdvertOfAuthenticatedUser(httpServletRequest, page, size, sort, type);
    }

    /**
     * Retrieves all adverts.
     * @param q search query (optional).
     * @param
     * @param page The page number for pagination. Default is 0.
     * @param size The size of each page. Default is 20.
     * @param sort The field to sort by. Default is 'category_id'.
     * @param type The sorting order, either 'asc' (ascending) or 'desc' (descending). Default is 'asc'.
     * @return A Page object containing AdvertResponse instances representing all adverts.
     */

    @GetMapping("/admin")
    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    public Page<AdvertResponse> getAllAdverts(@RequestParam(value = "q", required = false) String q,
                                              @RequestParam(value = "category_id" ) Category category_id,
                                              @RequestParam(value = "advert_type_id" ) AdvertType advert_type_id,
                                              @RequestParam(value = "price_start", required = false ) double price_start ,
                                              @RequestParam(value = "price_end", required = false) double price_end,
                                              @RequestParam(value = "status", required = false) int status,
                                              @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                              @RequestParam(value = "size", defaultValue = "20", required = false) int size,
                                              @RequestParam(value = "sort", defaultValue = "category_id", required = false) String sort,
                                              @RequestParam(value = "type", defaultValue = "asc", required = false) String type) {

        return advertService.getAllAdverts(q,category_id,advert_type_id, price_start, price_end, status, page,size,sort,type);

    }













}
