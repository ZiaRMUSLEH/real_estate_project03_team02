package com.project.real_estate_project03_team02.service.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.entity.concretes.business.TourRequest;
import com.project.real_estate_project03_team02.entity.concretes.user.User;
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
import java.time.LocalDate;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TourRequestServiceTest {
//
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
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testGetAllTourRequestOfAuthenticatedUser() {
//        // Mocking
//        String email = "ahmad@gmail.com";
//        int page = 0;
//        int size = 20;
//        String sort = "createdAt";
//        String type = "asc";
//        Page<TourRequest> tourRequestPage = mock(Page.class);
//        when(httpServletRequest.getAttribute("email")).thenReturn(email);
//        Pageable pageable = mock(Pageable.class);
//        when(pageableHelper.getPageableWithProperties(page, size, sort, type)).thenReturn(pageable);
//        when(tourRequestRepository.findAllByOwnerUserEmail(email, pageable)).thenReturn(tourRequestPage);
//        when(tourRequestMapper.mapTourRequestToTourRequestResponse(any())).thenReturn(new TourRequestResponse());
//
//        // Test
//        Page<TourRequestResponse> result = tourRequestService.getAllTourRequestOfAuthenticatedUser(httpServletRequest, page, size, sort, type);
//
//        // Verify
//        assertNotNull(result);
//        verify(httpServletRequest).getAttribute("email");
//        verify(pageableHelper).getPageableWithProperties(page, size, sort, type);
//        verify(tourRequestRepository).findAllByOwnerUserEmail(email, pageable);
//        verify(tourRequestMapper, times(0)).mapTourRequestToTourRequestResponse(any()); // Since we mocked an empty page, this shouldn't be called
//    }
//
//    @Test
//    void testSaveTourRequest() {
//        // Mocking
//        Advert advert = mock(Advert.class);
//        User ownerUser = mock(User.class);
//        User authenticatedUser = mock(User.class);
//        LocalDate tourDate = LocalDate.of(2014, 1, 1);
//        LocalTime tourTime = LocalTime.of(10, 10);
//        TourRequestRequest tourRequestRequest = new TourRequestRequest(tourDate, tourTime, 1L);
//        when(advertService.findById(any(Long.class))).thenReturn(advert);
//        when(advert.getUserId()).thenReturn(ownerUser);
//        when(httpServletRequest.getAttribute("email")).thenReturn("ahmad@gmail.com");
//        when(userService.findByEmail(anyString())).thenReturn(authenticatedUser);
//        TourRequest savedTourRequest = new TourRequest();
//        when(tourRequestRepository.save(any())).thenReturn(savedTourRequest);
//        when(tourRequestMapper.mapTourRequestToTourRequestResponse(savedTourRequest)).thenReturn(new TourRequestResponse());
//
//        // Test
//        ResponseMessage<TourRequestResponse> result = tourRequestService.saveTourRequest(httpServletRequest, tourRequestRequest);
//
//        // Verify
//        assertNotNull(result);
//        assertEquals(HttpStatus.CREATED, result.getHttpStatus());
//        verify(tourRequestRepository).save(any());
//        verify(tourRequestMapper).mapTourRequestToTourRequestResponse(any());
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
//        when(tourRequestRepository.findAll(any(Pageable.class))).thenReturn(tourRequestPage);
//        when(tourRequestPage.isEmpty()).thenReturn(false);
//        when(tourRequestPage.map(any())).thenReturn(mock(Page.class));
//
//        // Test
//        Page<TourRequestResponse> result = tourRequestService.getAllTourRequests(page, size, sort, type);
//
//        // Verify
//        assertNotNull(result);
//        verify(pageableHelper).getPageableWithProperties(page, size, sort, type);
//        verify(tourRequestRepository).findAll(any(Pageable.class));
//        verify(tourRequestPage).isEmpty();
//    }
}
