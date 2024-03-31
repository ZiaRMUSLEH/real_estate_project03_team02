package com.project.real_estate_project03_team02.controller.business;
import com.project.real_estate_project03_team02.entity.concretes.business.*;
import com.project.real_estate_project03_team02.entity.concretes.user.Role;
import com.project.real_estate_project03_team02.entity.concretes.user.User;
import com.project.real_estate_project03_team02.entity.enums.AdvertStatus;
import com.project.real_estate_project03_team02.entity.enums.AdvertTypes;
import com.project.real_estate_project03_team02.entity.enums.RoleType;
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
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

 class TourRequestControllerTest {

    @Mock
    private TourRequestService tourRequestService;

    @InjectMocks
    private TourRequestController tourRequestController;

     private MockMvc mockMvc;

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

//     @Test
//     @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
//     public void testGetAllTourRequests() {
//         // Mocking input parameters
//         int page = 0;
//         int size = 20;
//         String sort = "category_id";
//         String type = "asc";
//
//         // Mocking the returned Page object
//         Page<TourRequestResponse> mockedPage = mock(Page.class);
//
//         // Mocking the service method call
//         when(tourRequestService.getAllTourRequests(anyInt(), anyInt(), anyString(), anyString()))
//                 .thenReturn(mockedPage);
//
//         // Calling the controller method
//         Page<TourRequestResponse> result = tourRequestController.getAllTourRequests(page, size, sort, type);
//
//         // Verifying that the service method was called with correct parameters
//         verify(tourRequestService).getAllTourRequests(eq(page), eq(size), eq(sort), eq(type));
//
//         // Asserting the result
//         assertEquals(mockedPage, result);
//     }




//     @Test
//     void testGetTourRequestDetail() {
//         // Arrange
//         Long requestId = 1L;
//         HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
//
//         // Mock necessary entities
//         AdvertType advertType = AdvertType.builder()
//                 .title(AdvertTypes.RENT)
//                 .builtIn(false)
//                 .build();
//
//
//         Country country = new Country(1L,"United States");
//         City city = new City(1L,"New York",country);
//         District district = new District(1L,"Example District",city);
//
//         Role role1 = Role.builder()
//                 .roleName(RoleType.ADMIN)
//                 .build();
//         Role role2 = Role.builder()
//                 .roleName(RoleType.CUSTOMER)
//                 .build();
//
//         Set<Role> roles = new HashSet<>();
//         roles.add(role1);
//         roles.add(role2);
//
//         User user = User.builder()
//                 .firstName("John2")
//                 .lastName("Doe2")
//                 .email("john2.doe@example.com")
//                 .phone("124567890")
//                 .passwordHash("hashedPassword")
//                 .resetPasswordCode("resetCode")
//                 .builtIn(false)
//                 .createAt(LocalDateTime.now())
//                 .updateAt(null)
//                 .userRoles(roles)
//                 .build();
//
//         Category category = Category.builder()
//                 .title("Your Title")
//                 .icon("Your Icon")
//                 .builtIn(false)
//                 .seq(0)
//                 .slug("Your Slug")
//                 .isActive(true)
//                 .build();
//
//         Advert advert = Advert.builder()
//                 .title("Sample Title")
//                 .description("Sample Description")
//                 .slug("sample-slug")
//                 .price(100000.00)
//                 .status(AdvertStatus.ACTIVATED)
//                 .builtIn(false)
//                 .isActive(true)
//                 .viewCount(0)
//                 .location("Sample Location")
//                 .advertTypeId(advertType)
//                 .countryId(country)
//                 .cityId(city)
//                 .districtId(district)
//                 .userId(user)
//                 .categoryId(category)
//                 .createdAt(LocalDateTime.now())
//                 .build();
//
//         User ownerUser = User.builder()
//                 .firstName("John")
//                 .lastName("Doe")
//                 .email("john.doe@example.com")
//                 .phone("1234567890")
//                 .passwordHash("hashedPassword")
//                 .resetPasswordCode("resetCode")
//                 .builtIn(false)
//                 .createAt(LocalDateTime.now())
//                 .updateAt(LocalDateTime.now())
//                 .userRoles(roles)
//                 .build();
//
//         User guestUser = User.builder()
//                 .firstName("John1")
//                 .lastName("Doe1")
//                 .email("john1.doe@example.com")
//                 .phone("124567890")
//                 .passwordHash("hashedPassword")
//                 .resetPasswordCode("resetCode")
//                 .builtIn(false)
//                 .createAt(LocalDateTime.now())
//                 .updateAt(LocalDateTime.now())
//                 .userRoles(roles)
//                 .build();
//
//         Long id = 1L;
//         LocalDate tourDate = LocalDate.now();
//
//         TourRequestResponse expectedResponse = TourRequestResponse.builder()
//                 .id(id)
//                 .tourDate(tourDate)
//                 .advertId(advert)
//                 .ownerUserId(ownerUser)
//                 .guestUserId(guestUser)
//                 .build();
//
//         when(tourRequestService.getTourRequestDetail(httpServletRequest, requestId))
//                 .thenReturn(ResponseEntity.ok(expectedResponse));
//
//         // Act
//         ResponseEntity<TourRequestResponse> actualResponse = tourRequestController.getTourRequestDetail(requestId, httpServletRequest);
//
//         // Assert
//         assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
//         assertEquals(expectedResponse, actualResponse.getBody());
//
//         // Verify interactions
//         verify(tourRequestService, times(1)).getTourRequestDetail(httpServletRequest, requestId);
//         verifyNoMoreInteractions(tourRequestService);
//     }
//
//
//     @Test
//     void testGetTourRequestDetailById() {
//
//         // Mock necessary entities
//         AdvertType advertType = new AdvertType(1L,AdvertTypes.RENT,false);
//         Country country = new Country(1L,"United States");
//         City city = new City(1L,"New York",country);
//         District district = new District(1L,"Example District",city);
//
//         Role role1 = Role.builder()
//                 .id(1L)
//                 .roleName(RoleType.ADMIN)
//                 .build();
//         Role role2 = Role.builder()
//                 .id(2L)
//                 .roleName(RoleType.CUSTOMER)
//                 .build();
//
//         Set<Role> roles = new HashSet<>();
//         roles.add(role1);
//         roles.add(role2);
//
//         User user = User.builder()
//                 .id(1L)
//                 .firstName("John2")
//                 .lastName("Doe2")
//                 .email("john2.doe@example.com")
//                 .phone("124567890")
//                 .passwordHash("hashedPassword")
//                 .resetPasswordCode("resetCode")
//                 .builtIn(false)
//                 .createAt(LocalDateTime.now())
//                 .updateAt(null)
//                 .userRoles(roles)
//                 .build();
//
//         Category category = Category.builder()
//                 .id(1L)
//                 .title("Your Title")
//                 .icon("Your Icon")
//                 .builtIn(false)
//                 .seq(0)
//                 .slug("Your Slug")
//                 .isActive(true)
//                 .build();
//
//         Advert advert = Advert.builder()
//                 .id(1L)
//                 .title("Sample Title")
//                 .description("Sample Description")
//                 .slug("sample-slug")
//                 .price(100000.00)
//                 .status(AdvertStatus.ACTIVATED)
//                 .builtIn(false)
//                 .isActive(true)
//                 .viewCount(0)
//                 .location("Sample Location")
//                 .advertTypeId(advertType)
//                 .countryId(country)
//                 .cityId(city)
//                 .districtId(district)
//                 .userId(user)
//                 .categoryId(category)
//                 .createdAt(LocalDateTime.now())
//                 .build();
//
//         User ownerUser = user;
//
//         User guestUser = User.builder()
//                 .id(3L)
//                 .firstName("John1")
//                 .lastName("Doe1")
//                 .email("john1.doe@example.com")
//                 .phone("124567890")
//                 .passwordHash("hashedPassword")
//                 .resetPasswordCode("resetCode")
//                 .builtIn(false)
//                 .createAt(LocalDateTime.now())
//                 .updateAt(null)
//                 .userRoles(roles)
//                 .build();
//         Long requestId = 1L;
//         LocalDate tourDate = LocalDate.now();
//         TourRequestResponse expectedResponse = TourRequestResponse.builder()
//                 .id(requestId)
//                 .tourDate(tourDate)
//                 .advertId(advert)
//                 .ownerUserId(ownerUser)
//                 .guestUserId(guestUser)
//                 .build();
//
//         // Mock the service method call
//         when(tourRequestService.getTourRequestDetailById(requestId)).thenReturn(ResponseEntity.ok(expectedResponse));
//
//         // Act
//         ResponseEntity<TourRequestResponse> actualResponse = tourRequestController.getTourRequestDetailById(requestId);
//
//         // Assert
//         assertEquals(expectedResponse, actualResponse.getBody()); // Check if the response body matches the expected response
//         assertEquals(200, actualResponse.getStatusCodeValue()); // Check if the status code is 200 (OK)
//
//         // Verify that the service method was called exactly once with the specified requestId
//         verify(tourRequestService, times(1)).getTourRequestDetailById(requestId);
//     }


}
