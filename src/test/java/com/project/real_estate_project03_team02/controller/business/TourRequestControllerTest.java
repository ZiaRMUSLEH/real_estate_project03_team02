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
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TourRequestControllerTest {

//    @Mock
//    private TourRequestService tourRequestService;
//
//    @InjectMocks
//    private TourRequestController tourRequestController;
//
//
//    // Initialize mocks
//    @BeforeEach
//   void setUp() {
//
//        MockitoAnnotations.openMocks(this);
//
//        // Mocking behavior of tourRequestService
//        Page<TourRequestResponse> mockPage = new PageImpl<>(Collections.emptyList());
//        when(tourRequestService.getAllTourRequests(anyInt(), anyInt(), anyString(), anyString())).thenReturn(mockPage);
//    }
//
//    @Test
//    public void testGetAllTourRequestOfAuthenticatedUser() {
//        // Mock HttpServletRequest
//        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
//
//        // Mock response from tourRequestService
//        Page<TourRequestResponse> mockResponse = new PageImpl<>(Collections.emptyList());
//
//        // Mock behavior of tourRequestService.getAllTourRequestOfAuthenticatedUser
//        when(tourRequestService.getAllTourRequestOfAuthenticatedUser(eq(httpServletRequest), anyInt(), anyInt(), anyString(), anyString()))
//                .thenReturn(mockResponse);
//
//        // Call the controller method
//        Page<TourRequestResponse> result = tourRequestController.getAllTourRequestOfAuthenticatedUser(httpServletRequest, 0, 20, "category_id", "asc");
//
//        // Verify that the result is not null
//        assertNotNull(result);
//
//        // Verify that the controller method calls the service method with correct parameters
//        verify(tourRequestService).getAllTourRequestOfAuthenticatedUser(eq(httpServletRequest), eq(0), eq(20), eq("category_id"), eq("asc"));
//
//        // Verify that the controller response contains the same data as the mock response
//        assertEquals(mockResponse, result);
//    }
//
//
//    @Test
//    public void testGetAllTourRequests() {
//        // Given
//        int page = 0;
//        int size = 20;
//        String sort = "category_id";
//        String type = "asc";
//
//        // When
//        Page<TourRequestResponse> result = tourRequestController.getAllTourRequests(page, size, sort, type);
//
//        // Then
//        verify(tourRequestService, times(1)).getAllTourRequests(page, size, sort, type);
//        assertEquals(0, result.getTotalElements());
//    }
//
//
//    @Test
//    void getTourRequestDetailTest() {
//
//        // Mock HttpServletRequest
//        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
//
//        // Mock data
//        Long requestId = 123L;
//        TourRequestResponse tourRequestResponse = new TourRequestResponse(); // You need to create an instance of TourRequestResponse
//
//        // Mock the behavior of tourRequestService.getTourRequestDetail()
//        when(tourRequestService.getTourRequestDetail(httpServletRequest, requestId)).thenReturn(ResponseEntity.ok(tourRequestResponse));
//
//        // Call the method under test
//        ResponseEntity<TourRequestResponse> responseEntity = tourRequestController.getTourRequestDetail(requestId, httpServletRequest);
//
//        // Verify that tourRequestService.getTourRequestDetail() was called with the correct arguments
//        verify(tourRequestService).getTourRequestDetail(httpServletRequest, requestId);
//
//        // Assert the response
//        assertSame(responseEntity.getBody(), tourRequestResponse); // Assuming TourRequestResponse overrides equals() method
//    }
//
//    @Test
//    void getTourRequestDetailById_Admin_Success() {
//        // Arrange
//        Long requestId = 123L;
//        TourRequestResponse expectedResponse = new TourRequestResponse(/* Populate with expected data */);
//        when(tourRequestService.getTourRequestDetailById(requestId)).thenReturn(ResponseEntity.ok(expectedResponse));
//
//        // Act
//        ResponseEntity<TourRequestResponse> actualResponse = tourRequestController.getTourRequestDetailById(requestId);
//
//        // Assert
//        assertEquals(expectedResponse, actualResponse.getBody());
//        assertEquals(200, actualResponse.getStatusCodeValue());
//    }
//
//    @Test
//    void testSaveTourRequest() {
//
//
//        // Mock HttpServletRequest
//        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
//        TourRequestRequest tourRequestRequest = new TourRequestRequest(LocalDate.of(2024, 1, 1), LocalTime.of(10,0),1L );
//
//        // Mock behavior
//        TourRequestResponse expectedResponse = new TourRequestResponse(1L,LocalDate.of(2024, 1, 1),new Advert(),new User(),new User());
//        when(tourRequestService.saveTourRequest(httpServletRequest, tourRequestRequest)).thenReturn(new ResponseMessage<>(expectedResponse, "Success", HttpStatus.OK));
//
//        // Call the method
//        ResponseMessage<TourRequestResponse> actualResponse = tourRequestController.saveTourRequest(httpServletRequest, tourRequestRequest);
//
//        // Verify behavior
//        verify(tourRequestService, times(1)).saveTourRequest(httpServletRequest, tourRequestRequest);
//        assertEquals(HttpStatus.OK, actualResponse.getHttpStatus());
//        assertEquals("Success", actualResponse.getMessage());
//        assertEquals(expectedResponse, actualResponse.getObject());
//    }
//
//    @Test
//    void updateTourRequest_ValidInput_Success() {
//        // Arrange
//        Long id = 1L;
//        TourRequestRequest request = new TourRequestRequest(LocalDate.of(2024, 1, 1), LocalTime.of(10,0),1L );
//        TourRequestResponse expectedResponse = new TourRequestResponse(1L,LocalDate.of(2024, 1, 1),new Advert(),new User(),new User());
//        when(tourRequestService.updateTourRequest(request, id)).thenReturn(new ResponseMessage<>(HttpStatus.OK, "Tour request updated successfully", expectedResponse));
//
//        // Act
//        ResponseMessage<TourRequestResponse> responseMessage = tourRequestController.updateTourRequest(request, id);
//
//        // Assert
//        assertEquals(HttpStatus.OK, responseMessage.getHttpStatus());
//        assertEquals("Tour request updated successfully", responseMessage.getBody().getMessage());
//        assertEquals(expectedResponse, responseMessage.getObject(),responseMessage.getMessage(),);
//        verify(tourRequestService, times(1)).updateTourRequest(request, id);
//    }
//
//    @Test
//    void updateTourRequest_InvalidInput_Failure() {
//        // Arrange
//        Long id = 1L;
//        TourRequestRequest request = new TourRequestRequest(/* construct an invalid request object */);
//        when(tourRequestService.updateTourRequest(request, id)).thenReturn(/* construct a response for invalid input */);
//
//        // Act
//        ResponseEntity<ResponseMessage<TourRequestResponse>> responseEntity = tourRequestController.updateTourRequest(request, id);
//
//        // Assert
//        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
//        // Add more assertions based on the response for invalid input
//        verify(tourRequestService, times(1)).updateTourRequest(request, id);
//    }
//
//    @Test
//    public void testCancelTourRequest() {
//        // Mock HttpServletRequest
//        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
//
//        // Mock ID
//        Long id = 123L;
//
//        // Mock ResponseMessage
//        ResponseMessage<TourRequestResponse> expectedResponse = new ResponseMessage<>("Tour request cancelled successfully", new TourRequestResponse());
//
//        // Mock tourRequestService.cancelTourRequest to return expectedResponse
//        when(tourRequestService.cancelTourRequest(httpServletRequest, id)).thenReturn(expectedResponse);
//
//        // Call the method under test
//        ResponseMessage<TourRequestResponse> actualResponse = tourRequestController.cancelTourRequest(httpServletRequest, id);
//
//        // Verify that tourRequestService.cancelTourRequest was called with the correct arguments
//        verify(tourRequestService, times(1)).cancelTourRequest(httpServletRequest, id);
//
//        // Verify that the actualResponse matches the expectedResponse
//        assertEquals(expectedResponse, actualResponse);
//    }
//
//    @Test
//    public void approveTourRequest_ReturnsResponseMessage() {
//        // Arrange
//        Long id = 1L;
//        TourRequestResponse expectedResponse = new TourRequestResponse(/* Add necessary parameters */);
//        when(tourRequestService.approveTourRequest(id)).thenReturn(new ResponseMessage<>(expectedResponse));
//
//        // Act
//        ResponseMessage<TourRequestResponse> response = tourRequestController.approveTourRequest(id);
//
//        // Assert
//        assertNotNull(response);
//        assertEquals(expectedResponse, response.getData());
//        // Add more assertions as needed
//    }
//
//    @Test
//    void declineTourRequest_ValidId_ReturnsResponseMessage() {
//        // Arrange
//        Long requestId = 123L;
//        ResponseMessage<TourRequestResponse> expectedResponse = new ResponseMessage<>("Tour request declined", new TourRequestResponse());
//
//        // Mocking the service method
//        when(tourRequestService.declineTourRequest(requestId)).thenReturn(expectedResponse);
//
//        // Act
//        ResponseMessage<TourRequestResponse> actualResponse = tourRequestController.declineTourRequest(requestId);
//
//        // Assert
//        assertEquals(expectedResponse, actualResponse);
//    }
//
//    @Test
//    public void testDeleteTourRequestById() {
//        // Mock data
//        Long requestId = 1L;
//        ResponseMessage<TourRequestResponse> expectedResponse = new ResponseMessage<>("Success", new TourRequestResponse());
//
//        // Mocking service method
//        when(tourRequestService.deleteTourRequestById(anyLong())).thenReturn(expectedResponse);
//
//        // Call the method
//        ResponseMessage<TourRequestResponse> actualResponse = tourRequestController.deleteTourRequestById(requestId);
//
//        // Verify the result
//        assertEquals(expectedResponse, actualResponse);
//    }






}
