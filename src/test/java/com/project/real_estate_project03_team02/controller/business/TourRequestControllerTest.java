package com.project.real_estate_project03_team02.controller.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.entity.concretes.business.AdvertType;
import com.project.real_estate_project03_team02.entity.concretes.business.Category;
import com.project.real_estate_project03_team02.entity.concretes.business.Country;
import com.project.real_estate_project03_team02.entity.concretes.business.City;
import com.project.real_estate_project03_team02.entity.concretes.business.District;
import com.project.real_estate_project03_team02.entity.concretes.user.Role;
import com.project.real_estate_project03_team02.entity.concretes.user.User;
import com.project.real_estate_project03_team02.entity.enums.AdvertStatus;
import com.project.real_estate_project03_team02.entity.enums.AdvertTypes;
import com.project.real_estate_project03_team02.entity.enums.RoleType;
import com.project.real_estate_project03_team02.payload.request.business.TourRequestRequest;
import com.project.real_estate_project03_team02.payload.response.business.TourRequestResponse;
import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.service.business.TourRequestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TourRequestControllerTest {

    @Mock
    private TourRequestService tourRequestService;

    @InjectMocks
    private TourRequestController tourRequestController;

    @Test
    void testGetAllTourRequestOfAuthenticatedUser() {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setUserPrincipal(() -> "username");

        Page<TourRequestResponse> mockedPage = mock(Page.class);
        when(tourRequestService.getAllTourRequestOfAuthenticatedUser(request, 0, 20, "category_id", "asc"))
                .thenReturn(mockedPage);

        // Act
        Page<TourRequestResponse> result = tourRequestController.getAllTourRequestOfAuthenticatedUser(request, 0, 20, "category_id", "asc");

        // Assert
        assertEquals(mockedPage, result);
        verify(tourRequestService, times(1)).getAllTourRequestOfAuthenticatedUser(request, 0, 20, "category_id", "asc");
    }

    @Test
    void testGetAllTourRequests() {
        Page<TourRequestResponse> mockPage = mock(Page.class);
        when(tourRequestService.getAllTourRequests(anyInt(), anyInt(), anyString(), anyString())).thenReturn(mockPage);

        Page<TourRequestResponse> resultPage = tourRequestController.getAllTourRequests(0, 20, "tourTime", "asc");

        assertEquals(mockPage, resultPage);
    }

    @Test
    void testGetTourRequestDetail() {
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        TourRequestResponse expectedResponse = createSampleTourRequestResponse();

        ResponseEntity<TourRequestResponse> expectedEntity = ResponseEntity.ok(expectedResponse);
        when(tourRequestService.getTourRequestDetail(httpServletRequest, 1L)).thenReturn(expectedEntity);

        ResponseEntity<TourRequestResponse> actualResponseEntity = tourRequestController.getTourRequestDetail(1L, httpServletRequest);

        verify(tourRequestService).getTourRequestDetail(httpServletRequest, 1L);

        assertEquals(expectedEntity, actualResponseEntity);
    }

    @Test
    void testGetTourRequestDetailById() {
        Long id = 1L;
        TourRequestResponse mockResponse = createSampleTourRequestResponse();
        when(tourRequestService.getTourRequestDetailById(id)).thenReturn(ResponseEntity.ok(mockResponse));

        ResponseEntity<TourRequestResponse> responseEntity = tourRequestController.getTourRequestDetailById(id);

        assertEquals(ResponseEntity.ok(mockResponse), responseEntity);
    }

    @Test
    void testSaveTourRequest() {
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        TourRequestResponse mockResponse = createSampleTourRequestResponse();
        TourRequestRequest tourRequestRequest = createSampleTourRequestRequest();

        ResponseMessage<TourRequestResponse> expectedResponse = ResponseMessage.<TourRequestResponse>builder()
                .object(mockResponse)
                .message("This is a sample response message")
                .httpStatus(HttpStatus.OK)
                .build();

        when(tourRequestService.saveTourRequest(httpServletRequest, tourRequestRequest)).thenReturn(expectedResponse);

        ResponseMessage<TourRequestResponse> actualResponse = tourRequestController.saveTourRequest(httpServletRequest, tourRequestRequest);

        verify(tourRequestService, times(1)).saveTourRequest(httpServletRequest, tourRequestRequest);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testUpdateTourRequest() {
        Long id = 1L;
        TourRequestResponse mockResponse = createSampleTourRequestResponse();
        TourRequestRequest request = createSampleTourRequestRequest();
        ResponseMessage<TourRequestResponse> expectedResponse = ResponseMessage.<TourRequestResponse>builder()
                .object(mockResponse)
                .message("This is a sample response message")
                .httpStatus(HttpStatus.OK)
                .build();

        when(tourRequestService.updateTourRequest(request, id)).thenReturn(expectedResponse);

        ResponseMessage<TourRequestResponse> actualResponse = tourRequestController.updateTourRequest(request, id);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testCancelTourRequest() {
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        Long id = 1L;
        TourRequestResponse mockResponse = createSampleTourRequestResponse();

        ResponseMessage<TourRequestResponse> response = ResponseMessage.<TourRequestResponse>builder()
                .object(mockResponse)
                .message("Cancelled")
                .httpStatus(HttpStatus.OK)
                .build();

        when(tourRequestService.cancelTourRequest(mockRequest, 1L)).thenReturn(response);

        ResponseMessage<TourRequestResponse> actualResponse = tourRequestController.cancelTourRequest(mockRequest, 1L);

        assertEquals(response.getHttpStatus(), actualResponse.getHttpStatus());
        assertEquals("Cancelled", actualResponse.getMessage());
    }




    @Test
    void testApproveTourRequest() {
        // Mocked id
        Long id = 123L;

        // Mocking the response
        TourRequestResponse expectedResponse = createSampleTourRequestResponse();

        ResponseMessage<TourRequestResponse> expectedMessage = ResponseMessage.<TourRequestResponse>builder()
                .object(expectedResponse)
                .message("Approved")
                .httpStatus(HttpStatus.OK)
                .build();

        // Mocking the service method call
        when(tourRequestService.approveTourRequest(id)).thenReturn(expectedMessage);

        // Calling the controller method
        ResponseMessage<TourRequestResponse> actualResponse = tourRequestController.approveTourRequest(id);

        // Verifying the interaction
        verify(tourRequestService, times(1)).approveTourRequest(id);

        // Asserting the response
        assertEquals(expectedResponse, actualResponse.getObject());
    }

    @Test
    void testDeclineTourRequest() {
        // Mocked id
        Long id = 123L;

        // Mocking the response
        TourRequestResponse expectedResponse = createSampleTourRequestResponse();

        ResponseMessage<TourRequestResponse> expectedMessage = ResponseMessage.<TourRequestResponse>builder()
                .object(expectedResponse)
                .message("declined")
                .httpStatus(HttpStatus.OK)
                .build();

        // Mocking the service method call
        when(tourRequestService.approveTourRequest(id)).thenReturn(expectedMessage);

        // Calling the controller method
        ResponseMessage<TourRequestResponse> actualResponse = tourRequestController.approveTourRequest(id);

        // Verifying the interaction
        verify(tourRequestService, times(1)).approveTourRequest(id);

        // Asserting the response
        assertEquals(expectedResponse, actualResponse.getObject());
    }

    @Test
    void testDeleteTourRequestById() {
        // Arrange
        Long id = 1L;

        TourRequestResponse response = createSampleTourRequestResponse();

        ResponseMessage<TourRequestResponse> expectedResponse = ResponseMessage.<TourRequestResponse>builder()
                .object(response)
                .message("Deleted")
                .httpStatus(HttpStatus.OK)
                .build();
        when(tourRequestService.deleteTourRequestById(id)).thenReturn(expectedResponse);

        // Act
        ResponseMessage<TourRequestResponse> actualResponse = tourRequestController.deleteTourRequestById(id);

        // Assert
        assertEquals(expectedResponse, actualResponse);

    }




    private TourRequestResponse createSampleTourRequestResponse() {
        Long id = 1L;

        AdvertType advertType = AdvertType.builder()
                .title(AdvertTypes.RENT)
                .builtIn(false)
                .build();


        Country country = new Country(1L,"United States");
        City city = new City(1L,"New York",country);
        District district = new District(1L,"Example District",city);

        Role role1 = Role.builder()
                .roleName(RoleType.ADMIN)
                .build();
        Role role2 = Role.builder()
                .roleName(RoleType.CUSTOMER)
                .build();

        Set<Role> roles = new HashSet<>();
        roles.add(role1);
        roles.add(role2);

        User user = User.builder()
                .firstName("John2")
                .lastName("Doe2")
                .email("john2.doe@example.com")
                .phone("124567890")
                .passwordHash("hashedPassword")
                .resetPasswordCode("resetCode")
                .builtIn(false)
                .createAt(LocalDateTime.now())
                .updateAt(null)
                .userRoles(roles)
                .build();

        Category category = Category.builder()
                .title("Your Title")
                .icon("Your Icon")
                .builtIn(false)
                .seq(0)
                .slug("Your Slug")
                .isActive(true)
                .build();

        Advert advert = Advert.builder()
                .id(123456L)
                .title("Sample Title")
                .description("Sample Description")
                .slug("sample-slug")
                .price(100000.00)
                .status(AdvertStatus.ACTIVATED)
                .builtIn(false)
                .isActive(true)
                .viewCount(0)
                .location("Sample Location")
                .advertTypeId(advertType)
                .countryId(country)
                .cityId(city)
                .districtId(district)
                .userId(user)
                .categoryId(category)
                .createdAt(LocalDateTime.now())
                .build();

        User ownerUser = User.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .phone("1234567890")
                .passwordHash("hashedPassword")
                .resetPasswordCode("resetCode")
                .builtIn(false)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .userRoles(roles)
                .build();

        User guestUser = User.builder()
                .firstName("John1")
                .lastName("Doe1")
                .email("john1.doe@example.com")
                .phone("124567890")
                .passwordHash("hashedPassword")
                .resetPasswordCode("resetCode")
                .builtIn(false)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .userRoles(roles)
                .build();


        LocalDate tourDate = LocalDate.of(2024, 5, 12);


        return TourRequestResponse.builder()
                .id(id)
                .tourDate(tourDate)
                .advertId(advert)
                .ownerUserId(ownerUser)
                .guestUserId(guestUser)
                .build();
    }

    private TourRequestRequest createSampleTourRequestRequest() {
        return TourRequestRequest.builder()
                .tourDate(LocalDate.of(2024, 5, 12))
                .tourTime(LocalTime.of(10, 30))
                .advertId(123456L)
              .build();
    }
}

