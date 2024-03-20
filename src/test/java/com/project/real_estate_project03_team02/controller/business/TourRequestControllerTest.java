package com.project.real_estate_project03_team02.controller.business;
import com.project.real_estate_project03_team02.payload.response.business.TourRequestResponse;
import com.project.real_estate_project03_team02.service.business.TourRequestService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TourRequestControllerTest {

    @Mock
    private TourRequestService tourRequestService;

    @InjectMocks
    private TourRequestController tourRequestController;

    @Test
    void testGetAllTourRequestOfAuthenticatedUser() {
        // Arrange
        MockitoAnnotations.initMocks(this); // Initialize mocks

        // Mock HttpServletRequest
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setUserPrincipal(() -> "username"); // Mock authenticated user

        // Mock return value
        Page<TourRequestResponse> mockedPage = mock(Page.class);
        when(tourRequestService.getAllTourRequestOfAuthenticatedUser(request, 0, 20, "category_id", "asc")).thenReturn(mockedPage);

        // Act
        Page<TourRequestResponse> result = tourRequestController.getAllTourRequestOfAuthenticatedUser(request, 0, 20, "category_id", "asc");

        // Assert
        assertEquals(mockedPage, result); // Ensure that the returned page is as expected
        verify(tourRequestService, times(1)).getAllTourRequestOfAuthenticatedUser(request, 0, 20, "category_id", "asc");
        // Verify that the service method was called with the expected arguments
    }
}
