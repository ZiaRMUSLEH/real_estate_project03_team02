package com.project.real_estate_project03_team02.service.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.entity.concretes.business.TourRequest;
import com.project.real_estate_project03_team02.entity.concretes.user.User;
import com.project.real_estate_project03_team02.entity.enums.TourRequestStatus;
import com.project.real_estate_project03_team02.payload.mappers.business.TourRequestMapper;
import com.project.real_estate_project03_team02.payload.request.business.TourRequestRequest;
import com.project.real_estate_project03_team02.payload.response.business.TourRequestResponse;
import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.repository.business.TourRequestRepository;
import com.project.real_estate_project03_team02.service.helper.PageableHelper;
import com.project.real_estate_project03_team02.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TourRequestServiceTest {

//    @Mock
//    private TourRequestRepository tourRequestRepository;
//
//    @Mock
//    private PageableHelper pageableHelper;
//
//    @Mock
//    private TourRequestMapper tourRequestMapper;
//
//    @Mock
//    private UserService userService;
//
//    @Mock
//    private AdvertService advertService;
//
//    @Mock
//    private HttpServletRequest httpServletRequest;
//
//    @InjectMocks
//    private TourRequestService tourRequestService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    void testGetAllTourRequestOfAuthenticatedUser() {
//        // Mocking
//        Long userId = 1L;
//        int page = 0;
//        int size = 20;
//        String sort = "createdAt";
//        String type = "asc";
//        Page<TourRequest> tourRequestPage =mock(Page.class);
//        when(httpServletRequest.getAttribute("id")).thenReturn(userId);
//        when(pageableHelper.getPageableWithProperties(page, size, sort, type)).thenReturn(mock(Pageable.class));
//        when(tourRequestRepository.findAllByOwnerUserId(userId, pageableHelper.getPageableWithProperties(page, size, sort, type))).thenReturn(tourRequestPage);
//        when(tourRequestPage.map(any())).thenReturn(mock(Page.class));
//
//        // Test
//        Page<TourRequestResponse> result = tourRequestService.getAllTourRequestOfAuthenticatedUser(httpServletRequest, page, size, sort, type);
//
//        // Verify
//        assertNotNull(result);
//        verify(httpServletRequest).getAttribute("id");
//        verify(pageableHelper).getPageableWithProperties(page, size, sort, type);
//        verify(tourRequestRepository).findAllByOwnerUserId(userId, pageableHelper.getPageableWithProperties(page, size, sort, type));
//        verify(tourRequestPage).map(any());
//    }
//
//    @Test
//    void testSaveTourRequest() {
//        // Mocking
//        TourRequestRequest tourRequestRequest = new TourRequestRequest();
//        TourRequest tourRequest = new TourRequest();
//        Advert advert = mock(Advert.class);
//        User ownerUser = mock(User.class);
//        User authenticatedUser = mock(User.class);
//        when(tourRequestMapper.mapTourRequestRequestToTourRequest(tourRequestRequest)).thenReturn(tourRequest);
//        when(advertService.findById(any())).thenReturn(advert);
//        when(advert.getUserId()).thenReturn(ownerUser);
//        when(httpServletRequest.getAttribute("id")).thenReturn(1L);
//        when(userService.findById(any())).thenReturn(authenticatedUser);
//        when(tourRequestRepository.save(any())).thenReturn(tourRequest);
//        when(tourRequestMapper.mapTourRequestToTourRequestResponse(tourRequest)).thenReturn(mock(TourRequestResponse.class));
//
//        // Test
//        ResponseMessage<TourRequestResponse> result = tourRequestService.saveTourRequest(httpServletRequest, tourRequestRequest);
//
//        // Verify
//        assertNotNull(result);
//        assertEquals(HttpStatus.CREATED, result.getHttpStatus());
//        verify(tourRequest).setStatus(TourRequestStatus.PENDING);
//        verify(tourRequest).setAdvertId(advert);
//        verify(tourRequest).setOwnerUserId(ownerUser);
//        verify(tourRequest).setGuestUserId(authenticatedUser);
//        verify(tourRequest).setCreatedAt(any(LocalDateTime.class));
//        verify(tourRequestRepository).save(tourRequest);
//        verify(tourRequestMapper).mapTourRequestToTourRequestResponse(tourRequest);
//    }
//
//    @Test
//    void testGetAllTourRequests() {
//        // Mocking
//        int page = 0;
//        int size = 20;
//        String sort = "createdAt";
//        String type = "asc";
//        Page<TourRequest> tourRequestPage = mock(Page.class);
//        when(pageableHelper.getPageableWithProperties(page, size, sort, type)).thenReturn(mock(Pageable.class));
//        when(tourRequestRepository.findAll(pageableHelper.getPageableWithProperties(page, size, sort, type))).thenReturn(tourRequestPage);
//        when(tourRequestPage.isEmpty()).thenReturn(false);
//        when(tourRequestPage.map(any())).thenReturn(mock(Page.class));
//
//        // Test
//        Page<TourRequestResponse> result = tourRequestService.getAllTourRequests(page, size, sort, type);
//
//        // Verify
//        assertNotNull(result);
//        verify(pageableHelper).getPageableWithProperties(page, size, sort, type);
//        verify(tourRequestRepository).findAll(pageableHelper.getPageableWithProperties(page, size, sort, type));
//        verify(tourRequestPage).isEmpty();
//        verify(tourRequestPage).map(any());
//    }
}
