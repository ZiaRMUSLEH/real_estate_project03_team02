package com.project.real_estate_project03_team02.controller.business;

import com.project.real_estate_project03_team02.dto.CategoryDTO;
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
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/adverts")
@RequiredArgsConstructor
public class AdvertController {

    private final AdvertService advertService;


    /**
     * Endpoint to save a new advertisement.
     *
     * @param httpServletRequest The HttpServletRequest object containing the request information.
     * @param advertRequest The AdvertRequest object containing the details of the advertisement to be saved.
     * @return A ResponseMessage containing the result of the save operation and any relevant data.
     */
    @PostMapping("/save")
    public ResponseMessage<AdvertResponse> save(HttpServletRequest httpServletRequest, @RequestBody @Valid AdvertRequest advertRequest) {
        return advertService.save(httpServletRequest, advertRequest);
    }

    /**
     * Retrieves all adverts for everyone.
     * @param q search query (optional).
     * @param
     * @param page The page number for pagination. Default is 0.
     * @param size The size of each page. Default is 20.
     * @param sort The field to sort by. Default is 'category_id'.
     * @param type The sorting order, either 'asc' (ascending) or 'desc' (descending). Default is 'asc'.
     * @return A Page object containing AdvertResponse instances representing all adverts.
     */

    @GetMapping("")
    public Page<AdvertResponse> getAllAdvertsForEverybody(@RequestParam(value = "q", required = false) String q,
                                              @RequestParam(value = "category_id" ) Category category_id,
                                              @RequestParam(value = "advert_type_id" ) AdvertType advert_type_id,
                                              @RequestParam(value = "price_start", required = false ) double price_start ,
                                              @RequestParam(value = "price_end", required = false) double price_end,
                                              @RequestParam(value = "status", required = false) int status,
                                              @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                              @RequestParam(value = "size", defaultValue = "20", required = false) int size,
                                              @RequestParam(value = "sort", defaultValue = "category_id", required = false) String sort,
                                              @RequestParam(value = "type", defaultValue = "asc", required = false) String type){
        return advertService.getAllAdvertsForEverybody(q, category_id, advert_type_id, Optional.of(price_start), Optional.of(price_end), Optional.of(status), page, size, sort, type);
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
     * Retrieves all adverts for managers.
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
    public Page<AdvertResponse> getAllAdvertsForManagers(@RequestParam(value = "q", required = false) String q,
                                              @RequestParam(value = "category_id" ) Category category_id,
                                              @RequestParam(value = "advert_type_id" ) AdvertType advert_type_id,
                                              @RequestParam(value = "price_start", required = false ) double price_start ,
                                              @RequestParam(value = "price_end", required = false) double price_end,
                                              @RequestParam(value = "status", required = false) int status,
                                              @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                              @RequestParam(value = "size", defaultValue = "20", required = false) int size,
                                              @RequestParam(value = "sort", defaultValue = "category_id", required = false) String sort,
                                              @RequestParam(value = "type", defaultValue = "asc", required = false) String type) {

        return advertService.getAllAdvertsForManagers(q,category_id,advert_type_id, Optional.of(price_start), Optional.of(price_end), Optional.of(status), page,size,sort,type);

    }


    @GetMapping("/{slug}")
    @PreAuthorize("hasAnyAuthority('ANONYMOUS')")
    public AdvertResponse getAdvertBySlug(@PathVariable String slug){
        return advertService.getAdvertBySlug(slug);
    }

    @GetMapping("/{id}/auth")
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public ResponseEntity<AdvertResponse> getAdvertForAuthenticatedUser(@PathVariable Long id,
                                                                        HttpServletRequest httpServletRequest){
        return advertService.getAdvertForAuthenticatedUser(httpServletRequest, id);
    }


    @GetMapping("/{id}/admin")
    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    public ResponseEntity<AdvertResponse> getAdvertForManagersById(@PathVariable Long id){
        return advertService.getAdvertForManagersById(id);
    }


    @DeleteMapping("/admin/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    public ResponseMessage<AdvertResponse>deleteAdvertById(@PathVariable Long id){
        return advertService.deleteAdvertById(id);
    }

    @PutMapping("/admin/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    public AdvertResponse updateAdvertByManagers(@Valid @RequestBody AdvertRequest advertRequest, @PathVariable Long id) {
        return advertService.updateAdvertByManagers(advertRequest, id);
    }

    @PutMapping("/auth/{id}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public AdvertResponse updateAdvertByAuthenticatedUser(@Valid @RequestBody AdvertRequest advertRequest,
                                                          @PathVariable Long id,
                                                          HttpServletRequest httpServletRequest) {

        return advertService.updateAdvertByAuthenticatedUser(advertRequest, id, httpServletRequest);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getAdvertsGroupedByCategory(){
        List<CategoryDTO> groupedAdverts = advertService.getAdvertsGroupedByCategory();
        return ResponseEntity.ok(groupedAdverts);
    }

















}
