package com.project.real_estate_project03_team02.controller.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.entity.concretes.user.User;
import com.project.real_estate_project03_team02.payload.request.business.TourRequestRequest;
import com.project.real_estate_project03_team02.payload.response.business.TourRequestResponse;
import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.service.business.TourRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TourRequestControllerTest {

    @Mock
    private TourRequestService tourRequestService;

    @InjectMocks
    private TourRequestController tourRequestController;


    // Initialize mocks
    @BeforeEach
   void setUp() {

        MockitoAnnotations.openMocks(this);

        // Mocking behavior of tourRequestService
        Page<TourRequestResponse> mockPage = new PageImpl<>(Collections.emptyList());
        when(tourRequestService.getAllTourRequests(anyInt(), anyInt(), anyString(), anyString())).thenReturn(mockPage);
    }

    @Test
    public void testGetAllTourRequestOfAuthenticatedUser() {
        // Mock HttpServletRequest
        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();

        // Mock response from tourRequestService
        Page<TourRequestResponse> mockResponse = new PageImpl<>(Collections.emptyList());

        // Mock behavior of tourRequestService.getAllTourRequestOfAuthenticatedUser
        when(tourRequestService.getAllTourRequestOfAuthenticatedUser(eq(httpServletRequest), anyInt(), anyInt(), anyString(), anyString()))
                .thenReturn(mockResponse);

        // Call the controller method
        Page<TourRequestResponse> result = tourRequestController.getAllTourRequestOfAuthenticatedUser(httpServletRequest, 0, 20, "category_id", "asc");

        // Verify that the result is not null
        assertNotNull(result);

        // Verify that the controller method calls the service method with correct parameters
        verify(tourRequestService).getAllTourRequestOfAuthenticatedUser(eq(httpServletRequest), eq(0), eq(20), eq("category_id"), eq("asc"));

        // Verify that the controller response contains the same data as the mock response
        assertEquals(mockResponse, result);
    }




    @Test
    void testSaveTourRequest() {


        // Prepare test data
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        TourRequestRequest tourRequestRequest = new TourRequestRequest(LocalDate.of(2024, 1, 1), LocalTime.of(10,0),1L );

        // Mock behavior
        TourRequestResponse expectedResponse = new TourRequestResponse(1L,LocalDate.of(2024, 1, 1),new Advert(),new User(),new User());
        when(tourRequestService.saveTourRequest(httpServletRequest, tourRequestRequest)).thenReturn(new ResponseMessage<>(expectedResponse, "Success", HttpStatus.OK));

        // Call the method
        ResponseMessage<TourRequestResponse> actualResponse = tourRequestController.saveTourRequest(httpServletRequest, tourRequestRequest);

        // Verify behavior
        verify(tourRequestService, times(1)).saveTourRequest(httpServletRequest, tourRequestRequest);
        assertEquals(HttpStatus.OK, actualResponse.getHttpStatus());
        assertEquals("Success", actualResponse.getMessage());
        assertEquals(expectedResponse, actualResponse.getObject());
    }









    @Test
    public void testGetAllTourRequests() {
        // Given
        int page = 0;
        int size = 20;
        String sort = "category_id";
        String type = "asc";

        // When
        Page<TourRequestResponse> result = tourRequestController.getAllTourRequests(page, size, sort, type);

        // Then
        verify(tourRequestService, times(1)).getAllTourRequests(page, size, sort, type);
        assertEquals(0, result.getTotalElements());
    }
}
