package com.project.real_estate_project03_team02.service.business;

import com.project.real_estate_project03_team02.entity.concretes.business.*;
import com.project.real_estate_project03_team02.entity.concretes.user.User;
import com.project.real_estate_project03_team02.entity.enums.AdvertStatus;
import com.project.real_estate_project03_team02.exception.BadRequestException;
import com.project.real_estate_project03_team02.exception.ConflictException;
import com.project.real_estate_project03_team02.exception.ForbiddenException;
import com.project.real_estate_project03_team02.exception.ResourceNotFoundException;
import com.project.real_estate_project03_team02.payload.mappers.business.AdvertRequestToAdvertMapper;
import com.project.real_estate_project03_team02.payload.mappers.business.AdvertListToAdvertResponseListMapper;
import com.project.real_estate_project03_team02.payload.mappers.business.AdvertToAdvertResponseMapper;
import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import com.project.real_estate_project03_team02.payload.messages.SuccessMessages;
import com.project.real_estate_project03_team02.payload.request.business.AdvertRequest;
import com.project.real_estate_project03_team02.payload.response.business.AdvertResponse;
import com.project.real_estate_project03_team02.payload.response.business.CategoryResponseForAdvert;
import com.project.real_estate_project03_team02.payload.response.business.CityResponse;
import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.repository.business.AdvertRepository;
import com.project.real_estate_project03_team02.service.helper.AdvertServiceHelper;
import com.project.real_estate_project03_team02.service.helper.PageableHelper;
import com.project.real_estate_project03_team02.service.helper.SlugGenerator;
import com.project.real_estate_project03_team02.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AdvertService {

    private final AdvertRepository advertRepository;
    private final AdvertRequestToAdvertMapper advertRequestToAdvertMapper;
    private final UserService userService;
    private final SlugGenerator slugGenerator;
    private final AdvertToAdvertResponseMapper advertToAdvertResponseMapper;
    private final PageableHelper pageableHelper;

    private final AdvertServiceHelper advertServiceHelper;
    private final AdvertListToAdvertResponseListMapper advertListToAdvertResponseListMapper;

    private final String userName = "username";


    public ResponseMessage<AdvertResponse> save(HttpServletRequest httpServletRequest, AdvertRequest advertRequest) throws ConflictException {
        // Check if an advertisement with the same location already exists
        if (advertRepository.existsByLocation(advertRequest.getLocation())) {
            throw new ConflictException(String.format(ErrorMessages.ADVERT_ALREADY_EXIST, advertRequest.getLocation()));
        }

        // Map the advert request to an Advert entity
        Advert savedAdvert = advertRequestToAdvertMapper.mapAdvertRequestToAdvert(advertRequest, httpServletRequest);

        // Map the saved advert to response format
        AdvertResponse advertResponse = advertToAdvertResponseMapper.mapAdvertToAdvertResponse(savedAdvert);

        // Construct and return a ResponseMessage containing the saved advert response
        return ResponseMessage.<AdvertResponse>builder()
                .message(SuccessMessages.ADVERT_CREATED)
                .httpStatus(HttpStatus.CREATED)
                .object(advertResponse)
                .build();
    }




    /**
     * Retrieves all adverts associated with the authenticated user.
     *
     * @param httpServletRequest the HTTP servlet request containing user authentication information
     * @param page               the page number
     * @param size               the size of each page
     * @param sort               the sorting criteria
     * @param type               the type of sorting (ascending or descending)
     * @return a Page object containing advert responses
     */
    public Page<AdvertResponse> getAllAdvertOfAuthenticatedUser(HttpServletRequest httpServletRequest, int page, int size, String sort, String type) {
        String authenticatedUserEmail = (String) httpServletRequest.getAttribute("username");
        User authenticatedUser = userService.findByEmail(authenticatedUserEmail);
        Pageable pageable = pageableHelper.getPageableWithProperties(page, size, sort, type);
        return advertRepository.findAllByUserId(authenticatedUser, pageable).map(advertToAdvertResponseMapper::mapAdvertToAdvertResponse);
    }


    public Page<AdvertResponse> getAllAdverts(String q, Category categoryId, AdvertType advertTypeId, double priceStart, double priceEnd, int status, int page, int size, String sort, String type) {
        Pageable pageable = pageableHelper.getPageableWithProperties(page,size,sort,type);
        return advertRepository
                .findAll(pageable)
                .map(advertToAdvertResponseMapper::mapAdvertToAdvertResponse);

    }

    public Page<AdvertResponse> getAllAdvertsForEverybody(String q, Category categoryId, AdvertType advertTypeId, Optional<Double> priceStart, Optional<Double> priceEnd, Optional<Integer> status, int page, int size, String sort, String type) {
        Pageable pageable = pageableHelper.getPageableWithProperties(page, size, sort, type);
        if (q != null && !q.isEmpty()) {
            if (priceStart.isPresent() && priceEnd.isPresent() && status.isPresent()) {
                return advertRepository.findByTitleContainingAndIsActiveAndOptionalParameters(q, categoryId, advertTypeId, Optional.of(priceStart.get()), Optional.of(priceEnd.get()), Optional.of(status.get()), pageable)
                        .map(advertToAdvertResponseMapper::mapAdvertToAdvertResponse);
            } else {

                return advertRepository.findByTitleContainingAndIsActive(q, Boolean.TRUE, pageable)
                        .map(advertToAdvertResponseMapper::mapAdvertToAdvertResponse);

            }

        } else {

            if (priceStart.isPresent() && priceEnd.isPresent() && status.isPresent()) {
                return advertRepository.findByIsActiveAndCategoryIdAndAdvertTypeIdAndPriceBetweenAndStatus(Boolean.TRUE, categoryId, advertTypeId, priceStart.get(), priceEnd.get(), status.get(), pageable)
                        .map(advertToAdvertResponseMapper::mapAdvertToAdvertResponse);

            } else {
                return advertRepository.findByIsActive(Boolean.TRUE, pageable).map(advertToAdvertResponseMapper::mapAdvertToAdvertResponse);
            }

        }
    }

    public Page<AdvertResponse> getAllAdvertsForManagers(String q, Category categoryId, AdvertType advertTypeId, Optional<Double> priceStart, Optional<Double> priceEnd, Optional<Integer> status, int page, int size, String sort, String type) {
        Pageable pageable = pageableHelper.getPageableWithProperties(page, size, sort, type);
        if (q != null && !q.isEmpty()) {
            if (priceStart.isPresent() && priceEnd.isPresent() && status.isPresent()) {
                return advertRepository.findByTitleContainingAndIsActiveAndOptionalParameters(q, categoryId, advertTypeId, Optional.of(priceStart.get()), Optional.of(priceEnd.get()), Optional.of(status.get()), pageable)
                        .map(advertToAdvertResponseMapper::mapAdvertToAdvertResponse);
            } else {

                return advertRepository.findByTitleContainingAndIsActive(q, Boolean.TRUE, pageable)
                        .map(advertToAdvertResponseMapper::mapAdvertToAdvertResponse);

            }

        } else {

            if (priceStart.isPresent() && priceEnd.isPresent() && status.isPresent()) {
                return advertRepository.findByIsActiveAndCategoryIdAndAdvertTypeIdAndPriceBetweenAndStatus(Boolean.TRUE, categoryId, advertTypeId, priceStart.get(), priceEnd.get(), status.get(), pageable)
                        .map(advertToAdvertResponseMapper::mapAdvertToAdvertResponse);

            } else {
                return advertRepository.findByIsActive(Boolean.TRUE, pageable).map(advertToAdvertResponseMapper::mapAdvertToAdvertResponse);
            }

        }
    }

    public AdvertResponse getAdvertBySlug(String slug) {
        Advert advertBySlug = advertServiceHelper.findBySlug(slug);
        return advertToAdvertResponseMapper.mapAdvertToAdvertResponse(advertBySlug);

    }

    public ResponseEntity<AdvertResponse> getAdvertForAuthenticatedUser(HttpServletRequest httpServletRequest, Long id) {
        String authenticatedUserEmail = (String) httpServletRequest.getAttribute(userName);
        boolean isUserExist = userService.existByEmail(authenticatedUserEmail);
        if (!isUserExist) {
            throw new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_USER_MESSAGE_BY_EMAIL, authenticatedUserEmail));
        }
        Advert advert = advertRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_ADVERT_MESSAGE, id)));
        return ResponseEntity.ok(advertToAdvertResponseMapper.mapAdvertToAdvertResponse(advert));
    }

    public ResponseEntity<AdvertResponse> getAdvertForManagersById(Long id) {
        Advert advert = advertRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_TOUR_REQUEST, id)));
        return ResponseEntity.ok(advertToAdvertResponseMapper.mapAdvertToAdvertResponse(advert));
    }

    public long getCountAdvert() {
        return  advertRepository.count();
    }

    /**
     * Deletes an Advert by its ID.
     *
     * @param id The ID of the Advert to delete.
     * @return A ResponseMessage confirming the deletion of the Advert.
     */
    public ResponseMessage<AdvertResponse> deleteAdvertById(Long id) {
        Advert deletedAdvert = advertExists(id);
        advertRepository.deleteById(id);
        return ResponseMessage.<AdvertResponse>builder()
                .message(SuccessMessages.ADVERT_DELETED)
                .object(advertToAdvertResponseMapper.mapAdvertToAdvertResponse(deletedAdvert))
                .httpStatus(HttpStatus.OK)
                .build();
    }

    /**
     * Retrieves an Advert by its ID.
     *
     * @param id The ID of the Advert to retrieve.
     * @return Advert if found.
     * @throws ResourceNotFoundException if the Advert with the given ID is not found.
     */
    private Advert advertExists(Long id) {
        return advertRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_ADVERT_MESSAGE, id)));
    }

    public AdvertResponse updateAdvertByManagers(AdvertRequest advertRequest, Long id) {
        Advert advertById = advertServiceHelper.findById(id);
        Advert newAdvert = advertRequestToAdvertMapper.mapAdvertRequestToAdvert(advertRequest);
        if (!advertById.isBuiltIn()){
            newAdvert.setId(advertById.getId());
            newAdvert.setCreatedAt(advertById.getCreatedAt());
            newAdvert.setTitle(newAdvert.getTitle());
            newAdvert.setSlug(slugGenerator.generateSlug(newAdvert.getTitle()));
            newAdvert.setActive(newAdvert.isActive());
            newAdvert.setUpdatedAt(LocalDateTime.now());
            Advert savedAdvert=advertRepository.save(newAdvert);
            AdvertResponse response =advertToAdvertResponseMapper.mapAdvertToAdvertResponse(savedAdvert);
            return response;

        }else {
            throw new BadRequestException(String.format(ErrorMessages.ADVERT_IS_BUILT_IN,id));
        }

    }

    public AdvertResponse updateAdvertByAuthenticatedUser(AdvertRequest advertRequest, Long id, HttpServletRequest httpServletRequest) {
        String authenticatedUserEmail = (String) httpServletRequest.getAttribute(userName);
        User authenticatedUser = userService.findByEmail(authenticatedUserEmail);
        Advert advertById = advertServiceHelper.findById(id);
        if (authenticatedUser.getId() != advertById.getUserId().getId()) {
            throw new ForbiddenException(String.format(ErrorMessages.NO_AUTHORITY));
        }

        if (advertById.isBuiltIn()) {
            throw new BadRequestException(String.format(ErrorMessages.ADVERT_IS_BUILT_IN, id));
        }
        Advert newAdvert = advertRequestToAdvertMapper.mapAdvertRequestToAdvert(advertRequest);

        newAdvert.setId(advertById.getId());
        newAdvert.setCreatedAt(advertById.getCreatedAt());
        newAdvert.setTitle(newAdvert.getTitle());
        newAdvert.setSlug(slugGenerator.generateSlug(newAdvert.getTitle()));
        newAdvert.setActive(newAdvert.isActive());
        newAdvert.setUpdatedAt(LocalDateTime.now());
        newAdvert.setStatus(AdvertStatus.PENDING); // Reset status to PENDING
        newAdvert.setUserId(authenticatedUser);

        Advert savedAdvert = advertRepository.save(newAdvert);
        AdvertResponse response = advertToAdvertResponseMapper.mapAdvertToAdvertResponse(savedAdvert);
        return response;

    }

    public List<CategoryResponseForAdvert> getAdvertsGroupedByCategory() {

        return advertRepository.groupedAdvertsByCategory();

        //List<CategoryResponseForAdvert[]> groupedAdverts = advertRepository.groupedAdvertsByCategory();

//        return groupedAdverts.stream()
//                .map(categoryMapper::mapToCategoryResponseForAdvert)
//                .collect(Collectors.toList());

    }

    public List<AdvertResponse> getMostPopularAdverts(int amount) {
        List<Advert> popularAdverts = advertRepository.findMostPopularAdverts(amount);
        return advertListToAdvertResponseListMapper.mapAdvertListToAdvertResponseList(popularAdverts);
    }


    public List<CityResponse> getAdvertsGroupedByCity() {
        return advertRepository.groupedAdvertsByCity();
    }
}
